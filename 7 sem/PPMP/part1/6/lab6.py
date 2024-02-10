#-------------------------------------------------------------------------------
# Name:        lab6
# Purpose:
#
# Author:      Ksenya
#
# Created:     24.10.2023
# Copyright:   (c) Ksenya 2023
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import cv2

face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

smile_cascade = cv2.CascadeClassifier('haarcascade_smile.xml')

cap = cv2.VideoCapture(0)

while True:

    ret, img = cap.read()

    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    print('Number of detected faces:', len(faces))

    for (x,y,w,h) in faces:

        # draw a rectangle in a face
        cv2.rectangle(img,(x,y),(x+w,y+h),(0,255,255),2)
        cv2.putText(img, "Face", (x, y), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 255), 2)
        roi_gray = gray[y:y+h, x:x+w]
        roi_color = img[y:y+h, x:x+w]

        # detecting smile within the face roi
        smiles = smile_cascade.detectMultiScale(roi_gray, 1.8, 10)
        if len(smiles) > 0:
            print("smile detected")
            for (sx, sy, sw, sh) in smiles:
                cv2.rectangle(roi_color, (sx, sy), ((sx + sw), (sy + sh)), (0, 0, 255), 2)
                cv2.putText(roi_color, "smile", (sx, sy),
                cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)
        else:
            print("smile not detected")

    cv2.imshow('Smile Video', img)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()

cv2.destroyAllWindows()

