# Machine Learning Path's Overview  
As a machine learning engineers, we mainly contributed in making a model using Keras in TensorFlow to classify the Indonesian traditional cakes images that will be captured by Lookies. To encapsulate the machine learning path guidelines in this project, here are some points to consider:

## Machine Learning Job Desc  

## Collecting The Dataset  
For building Lookies prototype, we collected around 3500 images of 16 Indonesian traditional cakes varieties, which are:
1. Dadar gulung
2. Putu ayu
3. Kue lumpur
4. Risoles
5. Serabi
6. Bika ambon
7. Gethuk lindri
8. Klepon
9. Pastel
10. Putri salju
11. Cenil
12. Lemper
13. Kue lapis
14. Nagasari
15. Kastegel
16. Kue ape  

--bagian hadyan--  
The used dataset were split into 3 sets, that are training, validation, and testing set. The training set contains 105 to 200 images of each cake's class, whereas 20 images are included in validation and testing set respectively in each of the cake's class.

## The General Information of The Pre-trained Model Using EfficientNetB3  
The `EfficientNetB3`is a model that extracts some important features by adapting the equilibrium between the depth, width, and resolution of a network. We choose `EfficientNetB3` because this model had the best accuracy than the other models we tested [1].

## The Indonesian Traditional Cakes Model Notebook 
We utilized the `EfficientNetB3` pre-trained model to extract some feautures as it's mentioned above, then fine-tuned the model with adding the `CNN layers` on it. The built model generates 90.85% of accuracy in testing. Please refer to this [link](https://github.com/C22-PS225/Lookies-lihat-suka-makan/blob/main/machine_learning/building_model_90_accuracy.ipynb) to view the complete steps and result.

## The Notebook of Predicting The Indonesian Traditional Cakes Images  
After had the model, we use the model to predict some images. Please refer to this [notebook](https://github.com/C22-PS225/Lookies-lihat-suka-makan/blob/main/machine_learning/predict_Images.ipynb) to view the complete steps to predict images and also the result.

## References
[1][Baek, S., Jeon, J., Jeong, B., & Jeong, Y. S. (2021). Two-stage hybrid malware detection using deep learning. *Human-Centric Computing And Information Sciences, 11*, 2021.](http://hcisj.com/data/file/article/2021063002/11-27.pdf)
