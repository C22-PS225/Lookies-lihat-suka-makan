import numpy as np
from PIL import Image
import matplotlib
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
from keras_preprocessing import image
import tensorflow as tf
from efficientnet.tfkeras import EfficientNetB3
from io import BytesIO
from tensorflow.keras.applications.efficientnet import preprocess_input

def load_model():
  model_path = 'static/saved_model'
  #path nya sesuaiin ya sama saved modelnya nanti
  model = tf.saved_model.load(model_path)
  return model

def cake_prediction(image):
  img = mpimg.imread(image)
  img = tf.cast(img, tf.float32)
  img = tf.image.resize(img, [150, 150])
  img = img / 255.0
  img = np.expand_dims(img, axis=0)
  img = preprocess_input(img)

  cake_class = ['kue_ape', 'kue_bika_ambon', 'kue_cenil', 'kue_dadar_gulung', 'kue_gethuk_lindri', 'kue_kastengel', 'kue_klepon', 'kue_lapis', 'kue_lemper', 'kue_lumpur', 'kue_nagasari', 'kue_pastel', 'kue_putri_salju', 'kue_putu_ayu', 'kue_risoles', 'kue_serabi']
  model = load_model()
  pred = model(img)
  index = np.argmax(pred)
  pred_cake = cake_class[index]

  return pred_cake