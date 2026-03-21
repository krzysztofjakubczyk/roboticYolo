# wczytanie potrzebnych bibliotek
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt
from keras.src.callbacks import EarlyStopping
from keras.src.utils import to_categorical
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Flatten, add
from tensorflow.keras.layers import Dense
from tensorflow.keras.layers import Dropout
from tensorflow.keras import regularizers
from tensorflow.keras.callbacks import EarlyStopping

class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
fashion_mnist = tf.keras.datasets.fashion_mnist
(X_train, y_train), (X_val, y_val) = fashion_mnist.load_data()
X_train = X_train.astype('float32') / 255.0
X_val = X_val.astype('float32') / 255.0
y_train = to_categorical(y_train, len(class_names))
y_val = to_categorical(y_val, len(class_names))
model = Sequential()
model.add(Flatten(input_shape=(28, 28)))
model.add(Dense(128, activation='relu'))
model.add(Dense(10, activation = 'softmax'))
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])
model.summary()
history = model.fit(X_train,
                    y_train,
                    epochs=10,
                    verbose=1,
                    batch_size = 256,
                    validation_data = (X_val, y_val)
                   )


def draw_curves(history, key1='accuracy', ylim1=(0.8, 1.00),
                key2='loss', ylim2=(0.0, 1.0)):
    plt.figure(figsize=(12, 4))

    plt.subplot(1, 2, 1)
    plt.plot(history.history[key1], "r--")
    plt.plot(history.history['val_' + key1], "g--")
    plt.ylabel(key1)
    plt.xlabel('Epoch')
    plt.ylim(ylim1)
    plt.legend(['train', 'test'], loc='best')

    plt.subplot(1, 2, 2)
    plt.plot(history.history[key2], "r--")
    plt.plot(history.history['val_' + key2], "g--")
    plt.ylabel(key2)
    plt.xlabel('Epoch')
    plt.ylim(ylim2)
    plt.legend(['train', 'test'], loc='best')
plt.show()


# draw_curves(history, key1='accuracy', ylim1=(0.7, 0.95),
#             key2='loss', ylim2=(0.0, 0.8))
#
model2 = Sequential()
model2.add(Flatten(input_shape=(28, 28)))
model2.add(Dense(128, activation='relu'))
model2.add(Dense(10, activation='softmax'))

model2.compile(optimizer='adam',
               loss='categorical_crossentropy',
               metrics=['accuracy'])

EarlyStop = EarlyStopping(monitor='val_loss',
                          patience=3,
                          verbose=1)

history2 = model2.fit(X_train,
                      y_train,
                      epochs=50,
                      verbose=1,
                      batch_size=256,
                      validation_data=(X_val, y_val),
                      callbacks=[EarlyStop]
                      )

draw_curves(history2, key1='accuracy', ylim1=(0.7, 1.0),
            key2='loss', ylim2=(0.0, 0.8))
y_train_pred = model.predict(X_train)
y_val_pred = model.predict(X_val)


def plot_value_img(i, predictions, true_label, img):
    predictions, true_label, img = predictions[i], true_label[i], img[i]
    predicted_label = np.argmax(predictions)
    true_value = np.argmax(true_label)

    plt.figure(figsize=(12, 5))

    plt.subplot(1, 2, 1)

    plt.yticks(np.arange(len(class_names)), class_names)
    thisplot = plt.barh(range(10), predictions, color="gray")
    thisplot[predicted_label].set_color('r')
    thisplot[true_value].set_color('g')

    plt.subplot(1, 2, 2)

    plt.imshow(img, cmap=plt.cm.binary)
    if predicted_label == true_value:
        color = 'green'
    else:
        color = 'red'

    plt.xlabel("{} {:2.0f}% ({})".format(class_names[predicted_label],
                                         100 * np.max(predictions),
                                         class_names[true_value]),
               color=color)
    plt.show()


plot_value_img(1, y_val_pred, y_val, X_val)
model.add(Dropout(0.5, input_shape=(28,28)))

model_best = Sequential()
model_best.add(Flatten(input_shape=(28, 28)))
model_best.add(Dense(128, activation='relu'))
model_best.add(Dropout(0.3))
model_best.add(Dense(64, activation='relu'))
model_best.add(Dropout(0.3))
model_best.add(Dense(32, activation='relu'))
model_best.add(Dropout(0.3))
model_best.add(Dense(10, activation='softmax'))

model_best.compile(optimizer='adam',
                   loss='categorical_crossentropy',
                   metrics=['accuracy'])

EarlyStop = EarlyStopping(monitor='val_loss',
                          patience=5,
                          verbose=1)

history_best = model_best.fit(X_train,
                              y_train,
                              epochs=50,
                              verbose=1,
                              batch_size=1024,
                              validation_data=(X_val, y_val),
                              callbacks=[EarlyStop]
                              )

draw_curves(history_best, key1='accuracy', ylim1=(0.7, 1.0),
            key2='loss', ylim2=(0.0, 0.8))