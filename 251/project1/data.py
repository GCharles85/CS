# -*- coding: cp1252 -*-
'''data.py
Reads CSV files, stores data, access/filter data by variable name
Guyriano Charles
CS 251 Data Analysis and Visualization
Spring 2020
'''
import numpy as np
import csv


class Data:
    filepath = " "
    headers = []
    types = [] 
    data = []
    header2col = {}
        
    def __init__(self, filepath=None, headers=None, data=None, header2col=None):
        '''Data object constructor

        Parameters:
        -----------
        filepath: str or None. Path to data .csv file
        headers: Python list of strings or None. List of strings that explain the name of each
            column of data.
        data: ndarray or None. shape=(N, M).
            N is the number of data samples (rows) in the dataset and M is the number of variables
            (cols) in the dataset.
            2D numpy array of the dataset’s values, all formatted as floats.
            NOTE: In Week 1, don't worry working with ndarrays yet. Assume it will be passed in
                  as None for now.
        header2col: Python dictionary or None.
                Maps header (var str name) to column index (int).
                Example: "sepal_length" -> 0

        TODO:
        - Declare/initialize the following instance variables:
            - filepath
            - headers
            - types: Python list of strings (initialized to None).
                Possible values: 'numeric', 'string', 'enum', 'date'
            - data
            - header2col
            - Any others you find helpful in your implementation
        - If `filepath` isn't None, call the `read` method.
        '''
        if filepath != None:
            self.read(filepath)
        else:
            self.data = data
            self.headers = headers
            if self.headers is not None:
                self.headertocol()
    
    def read(self, filepath):
        '''Read in the .csv file `filepath` in 2D tabular format. Convert to numpy ndarray called
        `self.data` at the end (think of this as 2D array or table).

        Format of `self.data`:
            Rows should correspond to i-th data sample.
            Cols should correspond to j-th variable / feature.

        Parameters:
        -----------
        filepath: str or None. Path to data .csv file

        Returns:
        -----------
        None. (No return value).
            NOTE: In the future, the Returns section will be omitted from docstrings if
            there should be nothing returned

        TODO:
        - Read in the .csv file `filepath` to set `self.data`. Parse the file to only store
        numeric columns of data in a 2D tabular format (ignore non-numeric ones). Make sure
        everything that you add is a float.
        - Represent `self.data` (after parsing your CSV file) as an numpy ndarray. To do this:
            - At the top of this file write: import numpy as np
            - Add this code before this method ends: self.data = np.array(self.data)
        - Be sure to fill in the fields: `self.headers`, `self.types`, `self.data`, `self.header2col`.

        NOTE: You may wish to leverage Python's built-in csv module. Check out the documentation here:
        https://docs.python.org/3/library/csv.html

        NOTE: In any CS251 project, you are welcome to create as many helper methods as you'd like.
        The crucial thing is to make sure that the provided method signatures work as advertised.

        NOTE: You should only use the basic Python library to do your parsing.
        (i.e. no Numpy or imports other than csv).
        Points will be taken off otherwise.

        TIPS:
        - If you're unsure of the data format, open up one of the provided CSV files in a text editor
        or check the project website for some guidelines.
        - Check out the test scripts for the desired outputs.
        '''
        self.filepath = filepath
        
        indices = []
        htc = {}
        ind = []

        with open(filepath, 'r') as data:
            dataread = csv.reader(data, delimiter=',')
            data_l = [row for row in dataread]
    
        elsind = 0
        for item in data_l[1]:
            if item.strip() != "numeric":
                ind.append(elsind)
            elsind += 1
        
        for arr in data_l:
            indshrt = 0
            for num in ind:
                arr.pop(num - indshrt)
                indshrt += 1
                
        self.headers = list(map(str.strip, data_l[0]))
        indices = range(len(self.headers))
        
        for idx in indices:
            htc[self.headers[idx]] = idx - (indshrt - 1)
        self.header2col = htc
        
        self.types = list(map(str.strip, data_l[1]))
        data_l.remove(data_l[0])
        data_l.remove(data_l[0])
        data_l = [list(map(float, row)) for row in data_l]  
         
        self.data = np.array(data_l)

    def __str__(self):
        '''toString method

        (For those who don't know, __str__ works like toString in Java...In this case, it's what's
        called to determine what gets shown when a `Data` object is printed.)

        Returns:
        -----------
        str. A nicely formatted string representation of the data in this Data object.
            Only show, at most, the 1st 5 rows of data
            See the test code for an example output.
        '''
        if len(self.data) > 5:
            return str(self.filepath)+" " +str(self.data.shape)+'\n'+"Headers:"+'\n'+str(self.headers)+'\n'+"Types:"+'\n'+str(self.types)+'\n'+"------------------"+'\n'+"showing first five of "+str(len(self.data))+" rows" +'\n'+ str(self.head())
        else:
             return str(self.filepath)+" "+str(self.data.shape)+'\n'+"Headers:"+'\n'+str(self.headers)+'\n'+"Types:"+'\n'+str(self.types)+'\n'+"------------------"+'\n'+str(self.head())
            
    def headertocol(self):
        indices = range(len(self.headers))
        for idx in indices:
            self.header2col[self.headers[idx]] = idx 
    
    def get_headers(self):
        '''Get method for headers

        Returns:
        -----------
        Python list of str.
        '''
        return self.headers

    def get_types(self):
        '''Get method for data types of variables

        Returns:
        -----------
        Python list of str.
        '''
        return self.types

    def get_mappings(self):
        '''Get method for mapping between variable name and column index

        Returns:
        -----------
        Python dictionary. str -> int
        '''
        return self.header2col

    def get_num_dims(self):
        '''Get method for number of dimensions in each data sample

        Returns:
        -----------
        int. Number of dimensions in each data sample. Same thing as number of variables.
        '''
        return len(self.headers)

    def get_num_samples(self):
        '''Get method for number of data points (samples) in the dataset

        Returns:
        -----------
        int. Number of data samples in dataset.
        '''
        return len(self.data) 

    def get_sample(self, rowInd):
        '''Gets the data sample at index `rowInd` (the `rowInd`-th sample)

        Returns:
        -----------
        ndarray. shape=(num_vars,) The data sample at index `rowInd`
        '''
        return self.data[rowInd]

    def get_header_indices(self, headers):
        '''Gets the variable (column) indices of the str variable names in `headers`.

        Parameters:
        -----------
        headers: Python list of str. Header names to take from self.data

        Returns:
        -----------
        Python list of nonnegative ints. shape=len(headers). The indices of the headers in `headers`
            list.
        '''
        header_ind = []
        for item in headers:
            if item in self.header2col:
                header_ind.append(self.header2col[item])
        return header_ind

    def get_all_data(self):
        '''Gets a copy of the entire dataset

        (Week 2)

        Returns:
        -----------
        ndarray. shape=(num_data_samps, num_vars). A copy of the entire dataset.
            NOTE: This should be a COPY, not the data stored here itself.
            This can be accomplished with numpy's copy function.
        '''
        return np.copy(self.data)

    def head(self):
        '''Return the 1st five data samples (all variables)

        (Week 2)

        Returns:
        -----------
        ndarray. shape=(5, num_vars). 1st five data samples.
        '''
        return self.data[0:5]
                

    def tail(self):
        '''Return the last five data samples (all variables)

        (Week 2)

        Returns:
        -----------
        ndarray. shape=(5, num_vars). Last five data samples.
        '''
       
        if len(self.data) >= 5:
            return self.data[len(self.data) - 5:len(self.data)]
        else:
            return self.data

    def select_data(self, headers, rows=[]):
        '''Return data samples corresponding to the variable names in `headers`.
        If `rows` is empty, return all samples, otherwise return samples at the indices specified
        by the `rows` list.

        (Week 2)

        For example, if self.headers = ['a', 'b', 'c'] and we pass in header = 'b', we return
        column #2 of self.data. If rows is not [] (say =[0, 2, 5]), then we do the same thing,
        but only return rows 0, 2, and 5 of column #2.

        Parameters:
        -----------
            headers: Python list of str. Header names to take from self.data
            rows: Python list of int. Indices of subset of data samples to select.
                Empty list [] means take all rows

        Returns:
        -----------
        ndarray. shape=(num_data_samps, len(headers)) if rows=[]
                 shape=(len(rows), len(headers)) otherwise
            Subset of data from the variables `headers` that have row indices `rows`.

        Hint: For selecting a subset of rows from the data ndarray, check out np.ix_
        '''
        ind = []
        cr = []
        for item in headers:
           ind.append(self.headers.index(item))
        if rows == []:
            return self.data[:,ind]
        cr = np.ix_(rows, ind)
        return self.data[cr]
