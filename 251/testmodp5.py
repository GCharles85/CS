import matplotlib.pyplot as plt
import numpy as np


data = [[1,2],
        [2,2],
        [3,5],
        [3,6]]
data = np.array(data)
labels = [0,1,3,1]
#labels = np.array(labels)
#k = 3
#a = a.reshape((k,1))
#a = [a[labels] = data
u = np.unique(labels,return_counts=True)
#data = data[indices]
a = data[[0,1,2,3]]
a = np.split(a,2)
#a = np.array(a)
a  = np.mean(a,axis=1)
print(a)
