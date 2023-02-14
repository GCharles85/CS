# -*- coding: cp1252 -*-
'''transformation.py
Perform projections, translations, rotations, and scaling operations on Numpy ndarray data.
Guyriano Charles
CS 251 Data Analysis Visualization, Spring 2020
'''
import numpy as np
import matplotlib.pyplot as plt
import analysis
import data 


class Transformation(analysis.Analysis):
    data = None
    data_o = None
    
    def __init__(self, data_orig, data=None):
        '''Constructor for a Transformation object

        Parameters:
        -----------
        data_orig: Data object. shape=(N, num_vars).
            Contains the original dataset (only containing all the numeric variables
            — `num_vars` in total).
        data: Data object (or None). shape=(N, num_proj_vars).
            Contains all the data samples as the original, but ONLY A SUBSET of the variables.
            (`num_proj_vars` in total). `num_proj_vars` <= `num_vars`

        TODO:
        - Pass `data` to the superclass constructor.
        - Create an instance variables for `data_orig`.
        '''
        self.data = data
        self.data_o = data_orig 
        super().__init__(data)

    def project(self, headers):
        '''Project the data on the list of data variables specified by `headers` — i.e. select a
        subset of the variables from the original dataset. In other words, populate the instance
        variable `self.data`.

        Parameters:
        -----------
        headers: Python list of str. len(headers) = `num_proj_vars`, usually 1-3 (inclusive), but
            there could be more.
            A list of headers (strings) specifying the feature to be projected onto each axis.
            For example: if headers = ['hi', 'there', 'cs251'], then the data variables
                'hi' becomes the 'x' variable,
                'there' becomes the 'y' variable,
                'cs251' becomes the 'z' variable.
            The length of the list dictates the number of dimensions onto which the dataset is
            projected —� having 'y' and 'z' variables are optional.

        HINT: Update self.data with a new Data object and fill in appropriate optional parameters
        (except for `filepath`)

        TODO:
        - Create a new `Data` object that you assign to `self.data` (project data onto the `headers`
        variables).
        - Make sure that you create 'valid' values for all the `Data` constructor optional parameters
        (except you dont need `filepath` because it is not relevant).
        '''
        subset = self.data_o.select_data(headers)
        self.data = data.Data(data=subset, headers=headers)
        
    def get_data_homogeneous(self):
        '''Helper method to get a version of the projected data array with an added homogeneous
        coordinate. Useful for homogeneous transformations.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars+1). The projected data array with an added 'fake variable'
        column of ones on the right-hand side.
            For example: If we have the data SAMPLE (just one row) in the projected data array:
            [3.3, 5.0, 2.0], this sample would become [3.3, 5.0, 2.0, 1] in the returned array.

        NOTE:
        - Do NOT update self.data with the homogenous coordinate.
        '''
        num_rows = np.size(self.data.get_all_data(), 0)
        hc = np.ones((num_rows,1))
        return np.hstack((self.data.get_all_data(),hc))

    def translation_matrix(self, headers, magnitudes):
        ''' Make an M-dimensional homogeneous transformation matrix for translation,
        where M is the number of features in the projected dataset.

        Parameters:
        -----------
        headers: Python list of str.
            Specifies the variables along which the projected dataset should be translated.
        magnitudes: Python list of float.
            Translate corresponding variables in `headers` (in the projected dataset) by these
            amounts.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars+1). The transformation matrix.

        NOTE: This method just creates the translation matrix. It does NOT actually PERFORM the
        translation!
        '''
        sub_data = self.data.get_all_data()
        idm = np.eye(np.size(sub_data,1)+1)
        ind = self.data.get_header_indices(headers)
        arr = np.zeros(np.size(idm,0))
        np.put(arr,ind,magnitudes)
        arr = np.array(arr)
        idm[:,np.size(idm,1)-1] = np.add(arr, idm[:,np.size(idm,1)-1])
        return idm     

    def scale_matrix(self, headers, magnitudes):
        '''Make an M-dimensional homogeneous scaling matrix for scaling, where M is the number of
        variables in the projected dataset.

        Parameters:
        -----------
        headers: Python list of str.
            Specifies the variables along which the projected dataset should be scaled.
        magnitudes: Python list of float.
            Scale corresponding variables in `headers` (in the projected dataset) by these amounts.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars+1). The scaling matrix.

        NOTE: This method just creates the scaling matrix. It does NOT actually PERFORM the scaling!
        '''
        sub_data = self.data.get_all_data()
        idm = np.eye(np.size(sub_data,1)+1)
        ind = np.array(self.data.get_header_indices(headers))
       
        idm[0:np.size(idm,0)-1,0:np.size(idm,0)-1] = idm[0:np.size(idm,0)-1,0:np.size(idm,0)-1]*magnitudes
        return idm
        

    def rotation_matrix_3d(self, header, degrees):
        '''Make an 3-D homogeneous rotation matrix for rotating the projected data about the ONE
        axis/variable `header`.

        Parameters:
        -----------
        header: str. Specifies the variable about which the projected dataset should be rotated.
        degrees: float. Angle (in degrees) by which the projected dataset should be rotated.

        Returns:
        -----------
        ndarray. shape=(4, 4). The 3D rotation matrix with homogenous coordinate.

        NOTE: This method just creates the rotation matrix. It does NOT actually PERFORM the rotation!
        '''
        sub_data = self.data.get_all_data()
        idm = np.eye(np.size(sub_data,1)+1)
        ind = self.data.get_header_indices([header])
        degrees = degrees*(np.pi/180)
        if ind == [0]:
            idm[1,1] = np.cos(degrees)
            idm[1,2] = -np.sin(degrees)
            idm[2,1] = np.sin(degrees)
            idm[2,2] = np.cos(degrees)
        elif ind == [1]:
            idm[0,0] = np.cos(degrees)
            idm[0,2] = -np.sin(degrees)
            idm[2,0] = np.sin(degrees)
            idm[2,2] = np.cos(degrees)
        elif ind == [2]:
            idm[0,0] = np.cos(degrees)
            idm[0,1] = -np.sin(degrees)
            idm[1,0] = np.sin(degrees)
            idm[1,1] = np.cos(degrees)
        return idm

    def transform(self, C):
        '''Transforms the PROJECTED dataset by applying the homogeneous transformation matrix `C`.

        Parameters:
        -----------
        C: ndarray. shape=(num_proj_vars+1, num_proj_vars+1).
            A homogeneous transformation matrix.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars+1). The projected dataset after it has been transformed by `C`
        '''
        sub_data = self.get_data_homogeneous()
        tdata = (C @ sub_data.T).T
        return tdata

    def translate(self, headers, magnitudes):
        '''Translates the variables `headers` in projected dataset in corresponding amounts specified
        by `magnitudes`.

        Parameters:
        -----------
        headers: Python list of str.
            Specifies the variables along which the projected dataset should be translated.
        magnitudes: Python list of float.
            Translate corresponding variables in `headers` (in the projected dataset) by these amounts.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars). The translated data (with all variables in the projected).
            dataset. NOTE: There should be NO homogenous coordinate!

        TODO:
        - Use matrix multiplcation to translate the projected dataset, as advertised above.
        - Update `self.data` with a NEW Data object with the SAME `headers` and `header2col`
        dictionary as the current `self.data`, but DIFFERENT data (set to the data you
        transformed in this method). NOTE: The updated `self.data` SHOULD NOT have a homogenous
        coordinate!
        '''
        trans = self.translation_matrix(headers, magnitudes)
        transform = self.transform(trans) 
        self.data = data.Data(headers=self.data.get_headers(),data=transform[:,0:np.size(transform,1)-1])
        return self.data.get_all_data()

    def scale(self, headers, magnitudes):
        '''Scales the variables `headers` in projected dataset in corresponding amounts specified
        by `magnitudes`.

        Parameters:
        -----------
        headers: Python list of str.
            Specifies the variables along which the projected dataset should be scaled.
        magnitudes: Python list of float.
            Scale corresponding variables in `headers` (in the projected dataset) by these amounts.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars). The scaled data (with all variables in the projected).
            dataset. NOTE: There should be NO homogenous coordinate!

        TODO:
        - Use matrix multiplcation to scale the projected dataset, as advertised above.
        - Update `self.data` with a NEW Data object with the SAME `headers` and `header2col`
        dictionary as the current `self.data`, but DIFFERENT data (set to the data you
        transformed in this method). NOTE: The updated `self.data` SHOULD NOT have a
        homogenous coordinate!
        '''
        scale = self.scale_matrix(headers, magnitudes)
        transform = self.transform(scale) 
        self.data = data.Data(headers=self.data.get_headers(),data=transform[:,0:np.size(transform,1)-1])
        return self.data.get_all_data()

    def rotate_3d(self, header, degrees):
        '''Rotates the projected data about the variable `header` by the angle (in degrees)
        `degrees`.

        Parameters:
        -----------
        header: str. Specifies the variable about which the projected dataset should be rotated.
        degrees: float. Angle (in degrees) by which the projected dataset should be rotated.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars). The rotated data (with all variables in the projected).
            dataset. NOTE: There should be NO homogenous coordinate!

        TODO:
        - Use matrix multiplcation to rotate the projected dataset, as advertised above.
        - Update `self.data` with a NEW Data object with the SAME `headers` and `header2col`
        dictionary as the current `self.data`, but DIFFERENT data (set to the data you
        transformed in this method). NOTE: The updated `self.data` SHOULD NOT have a
        homogenous coordinate!
        '''
        rotate = self.rotation_matrix_3d(header, degrees)
        transform = self.transform(rotate) 
        self.data = data.Data(headers=self.data.get_headers(),data=transform[:,0:np.size(transform,1)-1])
        return self.data.get_all_data()

    def normalize_together(self):
        '''Normalize all variables in the projected dataset together by translating the global minimum
        (across all variables) to zero and scaling the global range (across all variables) to one.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars). The normalized version of the projected dataset.
        '''
        min_ = np.min(self.data.get_all_data())
        max_ = np.max(self.data.get_all_data())
        range_ = max_ - min_
        T = np.eye(np.size(self.data.get_all_data(),1)+1) 
        T[0:np.size(T,0)-1, np.size(T,0)-1] = -min_
        S = np.eye(np.size(self.data.get_all_data(),1)+1)
        S[0:np.size(S,0)-1,0:np.size(S,0)-1] = S[0:np.size(S,0)-1,0:np.size(S,0)-1]/range_
        N = S @ T
        data_norm = (N @ self.get_data_homogeneous().T).T
        return data_norm[0:,0:np.size(data_norm,1)-1]

    def normalize_separately(self):
        '''Normalize each variable separately by translating its local minimum to zero and scaling
        its local range to one.

        Returns:
        -----------
        ndarray. shape=(N, num_proj_vars). The normalized version of the projected dataset.
        '''
        mins = self.min(self.data.get_headers()).reshape(1,self.data.get_all_data().shape[1])
        maxs = self.max(self.data.get_headers()).reshape(1,self.data.get_all_data().shape[1])
        ranges = maxs - mins
        T = np.eye(np.size(self.data.get_all_data(),1)+1)
        T[0:np.size(T,0)-1, np.size(T,0)-1] = -mins[0,0:np.size(T,0)].T
        S = np.eye(np.size(self.data.get_all_data(),1)+1)
        S[0:np.size(S,0)-1,0:np.size(S,0)-1] = S[0:np.size(S,0)-1,0:np.size(S,0)-1]/ranges[0:,0:np.size(S,0)]
        N = S @ T
        data_norm = (N @ self.get_data_homogeneous().T).T
        return data_norm[0:,0:np.size(data_norm,1)-1]

    def scatter_color(self, ind_var, dep_var, c_var, title=None):
        '''Creates a 2D scatter plot with a color scale representing the 3rd dimension.

        Parameters
        -----------
        ind_var: str. Header of the variable that will be plotted along the X axis.
        dep_var: Header of the variable that will be plotted along the Y axis.
        c_var: Header of the variable that will be plotted along the color axis.
            NOTE: Section B (Linear Algebra): Use a ColorBrewer color palette (e.g. from the
            `palettable` library).
        title: str or None. Optional title that will appear at the top of the figure.
        '''
        arr = self.data.select_data([ind_var, dep_var])
        col = np.array(self.data.select_data([c_var])) 
        plt.scatter(arr[:,0], arr[:,1], c=col)
        if title != None:
            plt.title(title)
        plt.colorbar(label=c_var)
        plt.xlabel(ind_var)
        plt.ylabel(dep_var)
        plt.show()
        
        

    def heatmap(self, headers=None, title=None, cmap="gray"):
        '''Generates a heatmap of the specified variables (defaults to all). Each variable is normalized
        separately and represented as its own row. Each individual is represented as its own column.
        Normalizing each variable separately means that one color axis can be used to represent all
        variables, 0.0 to 1.0.

        Parameters:
        -----------
        headers: Python list of str (or None). (Optional) The variables to include in the heatmap.
            Defaults to all variables if no list provided.
        title: str. (Optional) The figure title. Defaults to an empty string (no title will be displayed).
        cmap: str. The colormap string to apply to the heatmap. Defaults to grayscale
            -- black (0.0) to white (1.0)

        Returns:
        -----------
        fig, ax: references to the figure and axes on which the heatmap has been plotted
        '''

        # Create a doppelganger of this Transformation object so that self.data
        # remains unmodified when heatmap is done
        data_clone = data.Data(headers=self.data.get_headers(),
                               data=self.data.get_all_data(),
                               header2col=self.data.get_mappings())
        dopp = Transformation(self.data, data_clone)
        dopp.set_data(data.Data(headers=self.data.get_headers(),data=dopp.normalize_separately(),
        header2col=self.data.get_mappings()))

        fig, ax = plt.subplots()
        if title is not None:
            ax.set_title(title)
        ax.set(xlabel="Individuals")

        # Select features to plot
        if headers is None:
            headers = dopp.data.headers
        m = dopp.data.select_data(headers)

        # Generate heatmap
        hmap = ax.imshow(m.T, aspect="auto", cmap=cmap)

        # Label the features (rows) along the Y axis
        y_lbl_coords = np.arange(m.shape[1]+1) - 0.5
        ax.set_yticks(y_lbl_coords, minor=True)
        y_lbls = [""] + headers
        ax.set_yticklabels(y_lbls )
        ax.grid(linestyle='none')

        # Create and label the colorbar
        cbar = fig.colorbar(hmap)
        cbar.ax.set_ylabel("Normalized Features")

        return fig, ax
