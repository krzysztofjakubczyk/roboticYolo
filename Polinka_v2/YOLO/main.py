from ultralytics import YOLO
import cv2
"""
test -> dataset ze zdjęciami, można też przetestować mp4
results -> zwraca zawartość test z nałożonymi boxami
"""

TOO_CLOSE = 0.5
CONFIDENCE = 0.5
DISTANCE = 9999

def is_too_close(box, frame_height):
    x1, y1, x2, y2 = map(int, box.xyxy[0])
    box_height = y2 - y1
    ratio = box_height / frame_height
    return ratio > TOO_CLOSE, round(ratio, 2)

# załaduj model
#model = YOLO("yolov8n.pt") #szybki, ale mało dokładny
model = YOLO("yolov8m.pt") #średniak
#model = YOLO("yolov8x.pt") #wolny, ale dokładny
#model = YOLO("runs/detect/train/weights/best.pt") #najlepszy wytrenowany model
"""
# info o modelu
model.info()

#trening basic
model.train(data="coco8.yaml", epochs=100, imgsz=640)

#trening better
model.train(
    data="coco128.yaml",
    epochs=50,
    imgsz=640,
    batch=16,
    lr0=0.001,
    patience=10,        # early stopping – zatrzyma jeśli brak poprawy
    augment=True,       # obroty, jasność, flip – więcej z mniejszej liczby zdjęć
    freeze=10,          # zamroź pierwsze 10 warstw – szybszy trening, zachowuje wiedzę COCO
)

#trening własny
model.train(
    data="dataset/dataset.yaml",
    epochs=50,
    imgsz=640,
    batch=16,
    lr0=0.001,
    patience=10,        # early stopping – zatrzyma jeśli brak poprawy
    augment=True,       # obroty, jasność, flip – więcej z mniejszej liczby zdjęć
    freeze=10,          # zamroź pierwsze 10 warstw – szybszy trening, zachowuje wiedzę COCO
)
"""

#show
#model("test/chair.jpg", show=True)
#input("type anything...")

# Folder ze zdjęciami
results = model("test/", imgsz=1280, augment=True)


# Wideo (mp4)
#results = model("test/video.mp4", stream=True)  # stream=True – ważne dla dużych plików!

# Kamera na żywo
#results = model(0, stream=True)  # 0 = domyślna kamera


#zapisz wynik do results
for i, r in enumerate(results):
    frame_height = r.orig_shape[0]  # oryginalna wysokość klatki
    too_close_detected = False
    for box in r.boxes:
        if float(box.conf) < CONFIDENCE:
            continue

        label = model.names[int(box.cls)]
        too_close, ratio = is_too_close(box, frame_height)

        if too_close:
            too_close_detected = True
            print(f"[{i}] ZA BLISKO: {label} (ratio={ratio})")
        else:
            print(f"[{i}] OK: {label} (ratio={ratio})")

    im = r.plot()
    cv2.imwrite(f"results/result_{i}.jpg", im)
