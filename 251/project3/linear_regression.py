# -*- coding: cp1252 -*-
'''linear_regression.py
Subclass of Analysis that performs linear regression on data
Guyriano Charles
CS 251 A/C Data Analysis Visualization, Spring 2020
'''
import numpy as np
import scipy.linalg
import matplotlib.pyplot as plt

import analysis


class LinearRegression(analysis.Analysis):
	'''
	Perform and store linear regression and related analyses
	'''

	def __init__(self, data):
		'''

		Parameters:
		-----------
		data: Data object. Contains all data samples and variables in a dataset.
		'''
		super().__init__(data)

		# ind_vars: Python list of strings.
		#   The headers of 1+ Independent variables (predictors) entered in the regression.
		self.ind_vars = None
		
		# dep_var: string. Header of the dependent variable predicted by the regression.
		self.dep_var = None

		# X: ndarray. shape=(num_data_samps, num_ind_vars)
		#   Raw data associated with the for independent (predictor) variable(s)
		# 	(Probably a column vector, but multiple ind vars are possible.)
		self.X = None
		
		# A: ndarray. shape=(num_data_samps, (num_ind_vars * polynomial_degree) + 1)
		#   Input matrix for independent (predictor) variables in linear regression
		#	Contains a column each term in the fitted function, e.g. a column of Xs
		#	and a column of 1s when solving a simple linear regression (y=mx+b), or
		# 	D+1 columns for a degree D polynomial fit.
		self.A = None

		# Y: ndarray. shape=(num_data_samps, 1)
		#   Column vector that contains dependent variable's true values (known outputs)
		self.Y = None

		# Y_pred: ndarray. shape=(num_data_samps, 1)
		#   Column vector that contains predicted outputs (from linear regression)
		self.Y_pred = None

		# R2: float. R^2 statistic
		self.R2 = None

		# R2_adj: float. Adjusted R^2 statistic (Week 2)
		self.R2_adj = None

		# slope: ndarray. shape=(num_ind_vars + 1, 1)
		#   Regression weight(s)
		self.W = None
		
		# R: ndarray. shape=(num_data_samps, 1)
		#   Residuals from regression fit
		self.R = None
		
		# d: int. Polynomial degree of regression model (Week 2)
		self.d = 1

	def simple_regression(self, ind_vars, dep_var):
		'''Performs a simple linear regression (of the form y = mx + b) on the 
		independent (predictor) variable(s) `ind_vars` and dependent variable 
		`dep_var` using the method `method`.

		Parameters:
		-----------
		ind_vars: Python list of strings. 1+ independent variables (predictors) entered in the regression.
			Variable names must match those used in the `self.data` object.
		dep_var: str. 1 dependent variable entered into the regression.
			Variable name must match one of those used in the `self.data` object.
			
		Returns:
		------------
		W: ndarray of weights that define the line of best fit

		TODO:
		- Use your data object to select the variable columns associated with the independent and
		dependent variable strings.
		- Perform linear regression to determine the weights that define the line of best fit.
		- Compute R^2 on the fit and the residuals.
		- By the end of this method, all instance variables should be set (see constructor).

		NOTE: Use other methods in this class where ever possible (do not write the same code twice!)
		'''
		self.ind_vars = ind_vars
		self.dep_var = dep_var
		data_x = self.data.select_data(ind_vars)
		self.X = data_x
		ones = np.ones((np.size(data_x,0),1))
		self.A = np.hstack((data_x,ones))
		data_y = self.data.select_data([dep_var])
		self.Y = data_y
		self.W = scipy.linalg.inv(self.A.T @ self.A) @ self.A.T @ self.Y
		self.Y_pred = self.predict(self.W,self.X)
		self.R2 = self.calc_r_squared(self.Y_pred)
		self.R = self.calc_residuals(self.Y_pred)
		self.R2_adj = self.R
		return self.W
		
	def predict(self, W=None, X=None):
		'''Use fitted linear regression model to predict the output (Y_pred) for each value in X.
		Generates the predictions Y_pred from the input matrix A (which may be different than X!) 
		and weight vector W.

		Parameters:
		-----------
		W: ndarray. shape=(num_ind_vars+1,)
			Linear regression weights (e.g. slope + intercept coefficients)
			for each independent var term AND the homogeneous coordinate
		X: ndarray. shape=(num_data_samps, num_ind_vars).
			If None, use self.X for the "x values" when making predictions.
			If not None, use X as "x values" used in making predictions.
		
		Returns
		-----------
		Y_pred: ndarray. shape=(num_data_samps,)
			Predicted y (dependent variable) values

		NOTE: You can write this method without any loops!
		'''
		ones = np.ones((np.size(self.X,0),1))
		if X is None:
		    X_mat = np.hstack((self.X,ones))
		elif self.d > 1:
		    X_mat = self.make_poly_matrix(X,self.d)
		else:
		    X_mat = np.hstack((X,ones))
		    
		Y_pred = (X_mat @ W)		    
		return Y_pred
		
	def calc_residuals(self, Y_pred):
		'''Determines the residual values from the linear regression model

		Parameters:
		-----------
		Y_pred: ndarray. shape=(num_data_samps, 1).
			Data column for model predicted dependent variable values.

		Returns
		-----------
		residuals: ndarray. shape=(num_data_samps, 1)
			Difference between the y values and the ones predicted by the regression model at the 
			data samples
		'''
		res = [self.Y[i] - Y_pred[i] for i in range(np.size(self.Y,0))]
		return np.array(res)
		
	def calc_r_squared(self, Y_pred):
		'''Computes the R^2 quality of fit statistic

		Parameters:
		-----------
		Y_pred: ndarray. shape=(num_data_samps,).
			Dependent variable values predicted by the linear regression model

		Returns:
		-----------
		R2: float.
			The R^2 statistic
		'''
		res = np.sum(self.calc_residuals(Y_pred)**2)
		smd = [(self.Y[i]-np.mean(self.Y))**2 for i in range(np.size(Y_pred,0))]
		smd = np.sum(smd)
		rsq = 1 - res/smd
		return rsq
		
	def calc_mse(self, X=None):
		'''Computes the mean squared error in the predicted y compared the actual y values.
		See notebook for equation.

		Parameters:
		-----------
		X: ndarray. shape=(anything, num_ind_vars)
			Data to get regression predictions on.
			If None, get predictions based on data used to fit model.

		Returns:
		-----------
		float. Mean squared error

		Hint: Make use of self.calc_residuals
		'''
		if X is not None:
		    return np.sum(self.calc_residuals(self.Y_pred)**2)/np.size(X,0)
		if X is None:
		    return np.sum(self.calc_residuals(self.Y_pred)**2)/np.size(self.X,0)
		    
	def scatter(self, ind_var, dep_var, title="", ind_var_index=0):
		'''Creates a scatter plot with a regression line to visualize the model fit.
		Assumes linear regression has been already run.
		
		Parameters:
		-----------
		ind_var: string. Independent variable name
		dep_var: string. Dependent variable name
		title: string. Title for the plot
		ind_var_index: int. Index of the independent variable in self.slope
			(which regression slope is the right one for the selected independent variable
			being plotted?)
			By default, assuming it is at index 0.

		TODO:
		- Use your scatter() in Analysis to handle the plotting of points. Note that it returns
		the (x, y) coordinates of the points.
		- Sample evenly spaced x values for the regression line between the min and max x data values
		- Use your regression slope, intercept, and x sample points to solve for the y values on the
		regression line.
		- Plot the line on top of the scatterplot.
		- Make sure that your plot has a title (with R^2 value in it)
		'''
		line_x = np.linspace(np.min(self.X[:,ind_var_index],0),np.max(self.X[:,ind_var_index],0),num=np.size(self.X[:,ind_var_index],0))
		line_y = self.predict(self.W,line_x.reshape(self.X.shape))
		if self.d == 1:
		    line_y = np.linspace(np.min(self.Y_pred,0),np.max(self.Y_pred,0),num=np.size(self.Y_pred,0))
		    x,y = analysis.Analysis(self.data).scatter(ind_var,dep_var,title = title + " R2 = %f"%self.R2)
		    plt.scatter(line_x,line_y, color = 'b',marker = 'd')
		elif self.d > 1:
		    x,y = analysis.Analysis(self.data).scatter(ind_var,dep_var,title = title + " R2 = %f"%self.R2)
		    plt.scatter(self.X[:,ind_var_index],self.Y_pred, color = 'b',marker = 'd')
		
		
	def pair_plot(self, data_vars, fig_sz=(12, 12)):
		'''Makes a pair plot with regression lines in each panel.
		There should be a len(data_vars) x len(data_vars) grid of plots, show all variable pairs
		on x and y axes.

		Parameters:
		-----------
		data_vars: Python list of strings. Variable names in self.data to include in the pair plot.
		fig_sz: tuple. len(fig_sz)=2. Width and height of the whole pair plot figure.
			This is useful to change if your pair plot looks enormous or tiny in your notebook!

		TODO:
		- Use your pair_plot() in Analysis to take care of making the grid of scatter plots.
		Note that this method returns the figure and axes array that you will need to superimpose
		the regression lines on each subplot panel.
		- In each subpanel, plot a regression line of the ind and dep variable. Follow the approach
		that you used for self.scatter. Note that here you will need to fit a new regression for
		every ind and dep variable pair.
		- Make sure that each plot has a title (with R^2 value in it)
		'''
		fig, axes = analysis.Analysis(self.data).pair_plot(data_vars,fig_sz)
		for i in range(len(data_vars)):
		    for j in range(len(data_vars)):
		       self.simple_regression([data_vars[j]],data_vars[i])
		       if j == i:
		           axes[i,j].clear()
		           axes[i,j].hist(self.Y_pred)
		           axes[i,j].axis("off")
		       else:
		           axes[i,j].scatter(self.data.data[:,j],self.data.data[:,i])
		           line_x = np.linspace(np.min(self.X,0),np.max(self.X,0),num=np.size(self.X,0))
		           line_y = np.linspace(np.min(self.Y_pred,0),np.max(self.Y_pred,0),num=np.size(self.Y_pred,0))
		           axes[i,j].scatter(line_x,line_y, color = 'r',marker = 'o')
		       axes[i,j].set_title("R2 = %f"%self.R2,fontsize = (fig_sz[0]/len(data_vars))*7)
		plt.show()
		
	def make_poly_matrix(self, X, d):
		'''Takes an independent variable data column vector `X` and transforms it into a matrix appropriate
		for a polynomial regression model of degree `d`.
		(Week 2)

		Parameters:
		-----------
		X: ndarray. shape=(num_data_samps, 1)
			Independent variable data column vector x
		d: int. Degree of polynomial regression model.

		Returns:
		-----------
		ndarray. shape=(num_data_samps, d)
			Independent variable data transformed for polynomial model.
			Example: if d=10, then the model should have terms in your regression model for
			x^10, x^9, ..., x^2, x^1, and a column of homogeneous coordinates (1s).
		'''
		ones = np.ones((np.size(X,0),1))
		X_temp = X
		for i in range(d-1):
		    X = np.hstack((X_temp**(i+2),X))
		X = np.hstack((X,ones))
		return X

	def poly_regression(self, ind_var, dep_var, d):
		'''Perform polynomial regression — generalizes self.linear_regression to polynomial curves
		(Week 2)
		NOTE: For single linear regression only (one independent variable only)

		Parameters:
		-----------
		ind_var: str. Independent variable entered in the single regression.
			Variable names must match those used in the `self.data` object.
		dep_var: str. Dependent variable entered into the regression.
			Variable name must match one of those used in the `self.data` object.
		d: int. Degree of polynomial regression model.
			 Example: if d=10, then the model should have terms in your regression model for
			 x^10, x^9, ..., x^2, x^1, and a column of homogeneous coordinates (1s).

		TODO:
		- This method should mirror the structure of self.linear_regression (compute all the same things)
		- Differences are:
			- You create the independent variable data matrix (self.A) with columns appropriate for
			polynomial regresssion. Do this with self.make_polynomial_matrix
			- You should programatically generate independent variable name strings based on the
			polynomial degree.
				Example: ['X_p3, X_p2, X_p1'] for a cubic polynomial model
			- You set the instance variable for the polynomial regression degree (self.d)
		'''
		self.ind_vars = ind_var
		self.dep_var = dep_var
		self.d = d
		data_x = self.data.select_data([ind_var])
		self.X = data_x
		self.A = self.make_poly_matrix(self.X,d)
		data_y = self.data.select_data([dep_var])
		self.Y = data_y
		self.W = scipy.linalg.inv(self.A.T @ self.A) @ self.A.T @ self.Y
		self.Y_pred = self.predict(self.W,self.X)
		self.R2 = self.calc_r_squared(self.Y_pred)
		self.R = self.calc_residuals(self.Y_pred)
		self.R2_adj = self.R
