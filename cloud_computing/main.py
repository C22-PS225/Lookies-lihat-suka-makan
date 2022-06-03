
import bcrypt
from flask_restful import Resource, Api
from flask import Flask, jsonify, make_response, request, redirect, url_for, session, flash
from flask_mysqldb import MySQL, MySQLdb

app = Flask(__name__)
api = Api(app)
app.secret_key = "INI RAHASIA"

app.config['MYSQL_HOST'] = "10.111.208.2"
app.config['MYSQL_UNIX_SOCKET'] = "/cloudsql/suksesatu:asia-southeast2:berhasil"
app.config['MYSQL_USER'] = 'bisa'
app.config['MYSQL_PASSWORD'] = 'bisa'
app.config['MYSQL_DB'] = 'lookies'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
mysql = MySQL(app)


class RegisterUser(Resource):
    #posting data
    def post(self):
        dataUsername = request.form.get('username')
        dataPassword = request.form.get('password')
        dataNama = request.form.get('nama')
        dataEmail = request.form.get('email')

        #check apakah username sudah ada
        if dataEmail and dataNama and dataUsername and dataPassword:
            #tulis data ke db.sqlite
            cur = mysql.connection.cursor()
            cur.execute("INSERT INTO users (nama,username,email,password) VALUES (%s,%s,%s,%s)" ,(dataNama,dataUsername, dataEmail, dataPassword)) 
            mysql.connection.commit()
            
            return make_response(jsonify({"msg":"Registrasi Berhasil"}),200)
        return jsonify({"msg":"Registrasi Gagal, Silahkan Cek Ulang"}),401

class LoginUser(Resource):
    def post(self):
        
        dataUsername = request.form.get('username')
        dataPassword = request.form.get('password')
        cur = mysql.connection.cursor()
        cur.execute("Select username, password from users where username=%s",(dataUsername,))
        user = cur.fetchone()
        cur.close()
        
        if user is not None and len(user) > 0 :
            if user['username'] == dataUsername and dataPassword == user['password'] :
                return make_response(jsonify({"msg" : "Login sukses"}),200)
            else :
                return make_response(jsonify({"msg" : "Login Gagal"}))
        else :
            return make_response(jsonify({"msg":"user tidak ada"}))
        
class HasilML(Resource):
    def get(self, hasil_ML):
        cur = mysql.connection.cursor()
        cur.execute("Select nama_kue, paragaf_1,paragaf_2,gambar from kue where hasil_ML=%s",(hasil_ML,))
        kue = cur.fetchone()
        cur.close()
        if kue is not None and len(kue) >1 :
            return make_response(jsonify(kue),200)
        else :
            return make_response(jsonify({"msg":"Kue tidak ditemukan"}),404)

class resep(Resource):
    def get(self, hasil_ML):
        cur = mysql.connection.cursor()
        cur.execute("Select nama_bahan, takaran from bahan where hasil_ML=%s",(hasil_ML,))
        bahan = cur.fetchall()
        cur.close()
        if bahan is not None and len(bahan) >1 :
            return make_response(jsonify(bahan),200)
        else :
            return make_response(jsonify({"msg":"Resep tidak ditemukan"}),404)

api.add_resource(RegisterUser, "/register", methods=["POST"])
api.add_resource(LoginUser, "/login", methods=["POST", "GET"])
api.add_resource(HasilML, "/hasilml/<string:hasil_ML>", methods=["GET"])
api.add_resource(resep, "/resep/<string:hasil_ML>", methods=["GET"])

#jalankan aplikasi
if __name__ == "__main__":
    app.run(debug=True)