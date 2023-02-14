import numpy as np

a = np.array([0,1,2,0,0,1,0])
b = np.argsort(a)
c = [[1,2],
     [2,2],
     [3,4],
     [9,10],
     [11,12],
     [2,2],
     [6,6]]
c = np.array(c)

sort = c[b]
counts = np.unique(a,return_counts=True)[1]
ind = np.cumsum(counts)
split = np.split(sort,ind[:-1])
numerator = np.array([np.sum(piece,axis=0) for piece in split]) + 1
print(numerator)
denom = np.sum(numerator,axis=1) + 2
print(denom)
lkh = numerator/denom[:,np.newaxis]
print(np.where(c!=2))
p = np.where(c!=2)
print(np.column_stack((p[0],p[1])))
