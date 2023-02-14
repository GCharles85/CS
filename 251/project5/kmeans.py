'''kmeans.py
Performs K-Means clustering
YOUR NAME HERE
CS 251 Data Analysis Visualization, Spring 2020
'''
import numpy as np
import matplotlib.pyplot as plt


class KMeans():
    def __init__(self, data=None):
        '''KMeans constructor

        (Should not require any changes)

        Parameters:
        -----------
        data: ndarray. shape=(num_samps, num_features)
        '''

        # k: int. Number of clusters
        self.k = None
        # centroids: ndarray. shape=(k, self.num_features)
        #   k cluster centers
        self.centroids = None
        # data_centroid_labels: ndarray. shape=(self.num_samps,)
        #   Holds index of the assigned cluster of each data sample
        self.data_centroid_labels = None

        # inertia: float.
        #   Mean squared distance between each data sample and its assigned (nearest) centroid
        self.inertia = None

        # data: ndarray. shape=(num_samps, num_features)
        self.data = data
        # num_samps: int. Number of samples in the dataset
        self.num_samps = None
        # num_features: int. Number of features (variables) in the dataset
        self.num_features = None
        if data is not None:
            self.num_samps, self.num_features = data.shape

    def set_data(self, data):
        '''Replaces data instance variable with `data`.

        Reminder: Make sure to update the number of data samples and features!

        Parameters:
        -----------
        data: ndarray. shape=(num_samps, num_features)
        '''
        self.data = data
        self.num_samps, self.num_features = data.shape

    def get_data(self):
        '''Get a COPY of the data

        Returns:
        -----------
        ndarray. shape=(num_samps, num_features). COPY of the data
        '''
        return self.data.copy()

    def get_centroids(self):
        '''Get the K-means centroids

        (Should not require any changes)

        Returns:
        -----------
        ndarray. shape=(k, self.num_features).
        '''
        return self.centroids

    def get_data_centroid_labels(self):
        '''Get the data-to-cluster assignments

        (Should not require any changes)

        Returns:
        -----------
        ndarray. shape=(self.num_samps,)
        '''
        return self.data_centroid_labels

    def dist_pt_to_pt(self, pt_1, pt_2):
        '''Compute the Euclidean distance between data samples `pt_1` and `pt_2`

        Parameters:
        -----------
        pt_1: ndarray. shape=(num_features,)
        pt_2: ndarray. shape=(num_features,)

        Returns:
        -----------
        float. Euclidean distance between `pt_1` and `pt_2`.

        NOTE: Implement without any for loops (you will thank yourself later since you will wait
        only a small fraction of the time for your code to stop running)
        '''
        return np.linalg.norm(pt_1 - pt_2)

    def dist_pt_to_centroids(self, pt, centroids):
        '''Compute the Euclidean distance between data sample `pt` and and all the cluster centroids
        self.centroids

        Parameters:
        -----------
        pt: ndarray. shape=(num_features,)
        centroids: ndarray. shape=(C, num_features)
            C centroids, where C is an int.

        Returns:
        -----------
        ndarray. shape=(C,).
            distance between pt and each of the C centroids in `centroids`.

        NOTE: Implement without any for loops (you will thank yourself later since you will wait
        only a small fraction of the time for your code to stop running)
        '''
        dist = (centroids.flatten() - np.tile(pt,len(centroids)).flatten())**2
        dist = np.sum(np.split(dist,len(centroids)),axis=1)**.5
        return dist

    def initialize(self, k):
        '''Initializes K-means by setting the initial centroids (means) to K unique randomly
        selected data samples

        Parameters:
        -----------
        k: int. Number of clusters

        Returns:
        -----------
        ndarray. shape=(k, self.num_features). Initial centroids for the k clusters.

        NOTE: Can be implemented without any for loops
        '''
        self.k = k
        inds = np.random.randint(self.data.shape[0],size=k)
        init = self.data[inds]
        return init

    def initialize_plusplus(self, k):
        '''Initializes K-means by setting the initial centroids (means) according to the K-means++
        algorithm

        (LA section only)

        Parameters:
        -----------
        k: int. Number of clusters

        Returns:
        -----------
        ndarray. shape=(k, self.num_features). Initial centroids for the k clusters.

        TODO:
        - Set initial centroid (i = 0) to a random data sample.
        - To pick the i-th centroid (i > 0)
            - Compute the distance between all data samples and i-1 centroids already initialized.
            - Create the distance-based probability distribution (see notebook for equation).
            - Select the i-th centroid by randomly choosing a data sample according to the probability
            distribution.
        '''
        pass

    def cluster(self, k=2, tol=1e-5, max_iter=1000, init_method='random', verbose=False):
        '''Performs K-means clustering on the data

        Parameters:
        -----------
        k: int. Number of clusters
        tol: float. Terminate K-means if the difference between all the centroid values from the
        previous and current time step < `tol`.
        max_iter: int. Make sure that K-means does not run more than `max_iter` iterations.
        init_method: str. How to initialize the cluster centroids.
            'random': Pick random samples (without replacement)
            'kmeans++': Use K-means++ initialization
        verbose: boolean. Print out debug information if set to True.

        Returns:
        -----------
        self.inertia. float. Mean squared distance between each data sample and its cluster mean
        int. Number of iterations that K-means was run for

        TODO:
        - Initialize K-means variables
        - Do K-means as long as the max number of iterations is not met AND the difference between
        the previous and current centroid values is > `tol`
        - Set instance variables based on computed values.
        (All instance variables defined in constructor should be populated with meaningful values)
        - Print out total number of iterations K-means ran for
        '''
        centroids = self.initialize(k)
        diff = 1
        iter_ = 0
        
        while (iter_ < max_iter) and (np.sum(diff) > tol):
            if verbose==True:
                print("Difference",np.sum(diff),'\n')
                print("centroids",centroids,'\n')
                print("iterations",iter_,'\n')
            prev_centroids = centroids
            iter_ = iter_+1
            dists = [self.dist_pt_to_centroids(pt,centroids) for pt in self.data]
            labels = np.argmin(dists,axis=1)
            centroids, diff = self.update_centroids(k,labels,prev_centroids)
            
        self.centroids = centroids
        self.data_centroid_labels = labels
        inertia = (self.data - self.centroids[self.data_centroid_labels])**2
        self.inertia = np.mean(np.sum(inertia,axis=1))
       
        print("K-means ran %d times"%iter_,'\n')
        return self.inertia,iter_

    def cluster_batch(self, k=2, n_iter=1, init_method='random', verbose=False):
        '''Run K-means multiple times, each time with different initial conditions.
        Keeps track of K-means instance that generates lowest inertia. Sets the following instance
        variables based on the best K-mean run:
        - self.centroids
        - self.data_centroid_labels
        - self.inertia

        Parameters:
        -----------
        k: int. Number of clusters
        n_iter: int. Number of times to run K-means with the designated `k` value.
        init_method: str. How to initialize the cluster centroids.
            'random': Pick random samples (without replacement)
            'kmeans++': Use K-means++ initialization
        verbose: boolean. Print out debug information if set to True.
        '''
        criteria = [[[self.get_centroids(), self.get_data_centroid_labels()], self.cluster(k=k,init_method=init_method,verbose=verbose)[0]] for num in range(n_iter)]
        criteria = np.array(criteria)
        ix = np.ix_(range(len(criteria)),[1])
        self.inertia = criteria[ix][np.argmin(criteria[ix])]
        self.centroids = criteria[np.argmin(criteria[ix])][0][0]
        self.data_centroid_labels = criteria[np.argmin(criteria[ix])][0][1]

    def update_labels(self, centroids):
        '''Assigns each data sample to the nearest centroid

        Parameters:
        -----------
        centroids: ndarray. shape=(k, self.num_features). Current centroids for the k clusters.

        Returns:
        -----------
        ndarray. shape=(self.num_samps,). Holds index of the assigned cluster of each data sample

        Example: If we have 3 clusters and we compute distances to data sample i: [0.1, 0.5, 0.05]
        labels[i] is 2. The entire labels array may look something like this: [0, 2, 1, 1, 0, ...]
        '''
        dists = [self.dist_pt_to_centroids(pt,centroids) for pt in self.data]
        self.data_centroid_labels = np.argmin(dists,axis=1)
        return  self.data_centroid_labels

    def update_centroids(self, k, data_centroid_labels, prev_centroids):
        '''Computes each of the K centroids (means) based on the data assigned to each cluster

        Parameters:
        -----------
        k: int. Number of clusters
        data_centroid_labels. ndarray. shape=(self.num_samps,)
            Holds index of the assigned cluster of each data sample
        prev_centroids. ndarray. shape=(k, self.num_features)
            Holds centroids for each cluster computed on the PREVIOUS time step

        Returns:
        -----------
        new_centroids. ndarray. shape=(k, self.num_features).
            Centroids for each cluster computed on the CURRENT time step
        centroid_diff. ndarray. shape=(k, self.num_features).
            Difference between current and previous centroid values
        '''
        ind = np.argsort(data_centroid_labels)
        clusters = self.data[ind]
        counts = np.unique(data_centroid_labels,return_counts=True)[1]
        counts = np.cumsum(counts)
        clusters = np.array(np.split(clusters,counts[:-1]))
        centroids = [np.mean(cluster,axis=0) for cluster in clusters]
        centroids = np.vstack(centroids)
        return centroids, centroids-prev_centroids

    def compute_inertia(self):
        '''Mean squared distance between every data sample and its assigned (nearest) centroid

        Parameters:
        -----------
        None

        Returns:
        -----------
        float. The average squared distance between every data sample and its assigned cluster centroid.
        '''
        inertia = (self.data - self.centroids[self.data_centroid_labels])**2
        return np.mean(np.sum(inertia,axis=1))

    def plot_clusters(self):
        '''Creates a scatter plot of the data color-coded by cluster assignment.


        TODO:
        - Plot samples belonging to a cluster with the same color.
        - Plot the centroids in black with a different plot marker.
        - The default scatter plot color palette produces colors that may be difficult to discern
        (especially for those who are colorblind). Make sure you change your colors to be clearly
        differentiable.
            (LA Section): You should use a palette Colorbrewer2 palette. Pick one with a generous
            number of colors so that you don't run out if k is large (e.g. 10).
        '''
        ind = np.argsort(self.data_centroid_labels)
        clusters = self.data[ind]
        counts = np.unique(self.data_centroid_labels,return_counts=True)[1]
        counts = np.cumsum(counts)
        clusters = np.split(clusters,counts[:-1])

        [plt.scatter(clusters[ind][:,0],clusters[ind][:,1]) for ind in range(self.k)]
        [plt.scatter(centroid[0],centroid[1],c='k') for centroid in self.centroids]
        plt.show()

    def elbow_plot(self, max_k):
        '''Makes an elbow plot: cluster number (k) on x axis, inertia on y axis.

        Parameters:
        -----------
        max_k: int. Run k-means with k=1,2,...,max_k.

        TODO:
        - Run k-means with k=1,2,...,max_k, record the inertia.
        - Make the plot with appropriate x label, and y label, x tick marks.
        '''
        inertia = [self.cluster(k=num+1)[0] for num in range(max_k)]
        plt.plot(np.array(range(max_k))+1,inertia)
        plt.xlabel("k clusters")
        plt.ylabel("Inertia")
        plt.show()

    def replace_color_with_centroid(self):
        '''Replace each RGB pixel in self.data (flattened image) with the closest centroid value.
        Used with image compression after K-means is run on the image vector.

        Parameters:
        -----------
        None

        Returns:
        -----------
        None
        '''
        self.data = self.centroids[self.data_centroid_labels].astype(int)
