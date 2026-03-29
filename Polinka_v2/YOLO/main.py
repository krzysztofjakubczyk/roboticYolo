from ultralytics import YOLO
import cv2
"""
test -> dataset ze zdjęciami, można też przetestować mp4
results -> zwraca zawartość test z nałożonymi boxami
"""


# załaduj model
#model = YOLO("yolov8n.pt") #szybki, ale mało dokładny
model = YOLO("yolov8m.pt") #średniak
#model = YOLO("yolov8x.pt") #wolny, ale dokładny
"""
# info o modelu
model.info()

#trening basic
results = model.train(data="coco8.yaml", epochs=100, imgsz=640)
"""

#show
#model("test/chair.jpg", show=True)
#input("type anything...")
results = model("test/chair.jpg")

#zapisz wynik do results
i = 0
for r in results:
    i+1
    im = r.plot()
    cv2.imwrite(f"results/result_{i}.jpg",im)
