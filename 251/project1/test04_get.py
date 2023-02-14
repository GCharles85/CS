'''test04_get.py
Test Data class get methods
CS 251 Data Analysis and Visualization
Spring 2020
Oliver Layton, Caitrin Eaton, Hannah Wolfe
'''
import numpy as np

from data import Data


def test_all_get_methods(iris_filename):
    iris_data = Data(iris_filename)

    print("Your iris headers are", iris_data.get_headers(), "and should be\n['sepal_length', 'sepal_width', 'petal_length', 'petal_width']\n")
    print("Your iris variable types are", iris_data.get_types(), "\nand should be\n['numeric', 'numeric', 'numeric', 'numeric']\n")
    print("Your iris variable mapping is", iris_data.get_mappings(), "\nand should be\n'sepal_length': 0, 'sepal_width': 1, 'petal_length': 2, 'petal_width': 3\n")
    print('Your data has', iris_data.get_num_samples(), 'samples and ', iris_data.get_num_dims(), 'variables/dimensions.\nIt should have 150 samples and 4 variables/dimensions.\n')
    print('Your 10th sample is', iris_data.get_sample(9), '\nand should be \n[4.9 3.1 1.5 0.1]\n')
    print('The indices of the headers sepal_width and petal_width are', iris_data.get_header_indices(["sepal_width", "petal_width"]), '\nand should be \n[1, 3]\n')


if __name__ == '__main__':
    print('---------------------------------------------------------------------------------------')
    print('Begining test 1 (Test all get methods)...')
    print('---------------------------------------------')
    data_file = "C:/Users/Gchar/Documents/Colby/CS/251/project1/data/iris.csv"
    test_all_get_methods(data_file)
    print('---------------------------------------------')
    print('Finished test 1!')
    print('---------------------------------------------------------------------------------------')
