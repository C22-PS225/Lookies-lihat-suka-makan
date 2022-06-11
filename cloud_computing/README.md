# Cloud Computing Job Desk

- Deploy Machine Learning model on Google Cloud Platform using Compute Engine
- Create SQL instance to store data and connect to compute engine via private IP Address
- Create connector using Serverless VPC Access
- Create endpoints for login, register, search cake, send the result of machine learning process and to give informations about the cake to frontend
- Create cloud storage to store cake images

# Process in Cloud Computing

![Untitled Diagram drawio(1)](https://user-images.githubusercontent.com/89336327/173192689-102be77a-ea41-4278-81a2-c45e20b4a98c.png)


# API Endpoint 
|   Endpoint   |   Method   |        Body Sent (JSON)         |                 Description                |
| :----------: | :--------: | :----------------------------:  | :----------------------------------------: |
|     /        |     GET    |             None                |           Testing Endpoint & Server        |
|   /login     | POST & GET |      username & password        |            Authentication for user         |
|  /register   |    POST    | username, password, email, nama |             Registration for user          |
| /predictkue  |    POST    |        file: Image file         |    HTTP POST REQUEST Prediction Endpoint   |
|/kue/nama_kue |     GET    |             None                | Give the response about cake's description |
|   /carikue   |    POST    |            String               |           do a search by cake name         |

# How to predict image with Postman
- Open Postman
- Enter URL request bar with `34.101.248.103:8080/predictkue`
- Select method POST
- Go to Body tab and select form-data
- Change key from form-data with file (it must be named `file`)
- Input the image that you want predict as a value of the key
- Send the request
