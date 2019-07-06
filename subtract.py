#!/usr/bin/python
from SimpleCV import *
import numpy as np
import cv2

img_cam0 = cv2.imread(r'./1.png',0)
img_cam1 = cv2.imread(r'./2.png', 0)
print('img0 type:',type(img_cam0), 'and shape:', img_cam0.shape)
print('img1 type:',type(img_cam1),'and shape:', np.shape(img_cam1))
print('\n')

#opencv subtraction

cv2_subt = cv2.subtract(img_cam0,img_cam1)
cv2_mean = cv2.mean(cv2_subt)

print('open cv mean is:', cv2_mean)
show_im(cv2_subt, 'cv2_subtr')

#np subtraction and mean

np_subtr = np.subtract(img_cam0, img_cam1)
np_mean = np.mean(np_subtr)

print('numpy mean is:', np_mean)
show_im(np_subtr, 'np_subtr')
