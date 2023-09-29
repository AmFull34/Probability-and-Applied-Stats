import java.util.ArrayList;

/**
 * The Stats Library Class is a custom class created to use in the Probability
 * and Applied Statistics course and beyond. The user can invoke its methods to
 * calculate several values associated with an object that represents a data set.
 * @param <E> The class will accept generics but works best with lists of numeric data types.
 */
public class StatsLibrary<E>{

    /**
     * findListSum method provides a helper method for summation of list elements.
     * @param anArrayList the list to sum values
     * @return the sum of list values.
     */
    public double findListSum(ArrayList<E> anArrayList){
        double sum = 0;
        if(!anArrayList.isEmpty()) {
            for (E element : anArrayList) {
                sum += (Double) element;
            }
        }
        return sum;
    }

    /**
     * findDifferenceOfValuesAndMean method subtracts the mean of values in a list
     * from each of those values. Used in calculating standard deviation of a dataset
     * @param anArrayList the list of values
     * @param mean mean of the passed list
     * @return the new list
     */
    public ArrayList<Double> findDifferenceOfValues(ArrayList<E> anArrayList, double mean){
        //create a list of equal size to hold new values
        ArrayList<Double> differenceList = new ArrayList<>(anArrayList.size());
        //for each value of the passed list,
        for (E element : anArrayList){
            //set the elements of the new list as difference of passed list value and mean
            double newValue = (Double) element - mean;
            differenceList.add(newValue);
        }
        //return the new list
        return differenceList;
    }
    /**
     * findMean computes the average of values in a list.
     * @param anArrayList the list to iterate
     * @return the average
     */
    public double findMean(ArrayList<E> anArrayList){
        return findListSum(anArrayList) / anArrayList.size();
    }

    //findMode method
        //what is the best way?
        //sort then count each?
        //would need generic comparator
        //possibly use comparator with toString?
        //turn the list into a string and compare using substring?
        //otherwise use a list by unique elements then count approach

    //findMedian method
        //sort then find middle
        //return middle

    //computeStandardDeviation method
    public double computeStandardDeviation(ArrayList<E> anArrayList) {
        double result = 0;
        //for each loop the list to get sum
        //divide to find mean
        double mean = findMean(anArrayList);
        //for each element, subtract the mean from it to get a new list of values

        //square each value
        //add the values
        //find the new mean
        //square the new mean
        //return the result
        return result;
    }

    //findVariance method

    //calculateFactorial method
    //does what it says.
    //use big integer object to avoid hitting integer limit on primitive int
    //lets use recursion

    //findIntersection method
        //compare each value
        //if they match add to a new list
        //return this list

    //findUnion method
        //add each value to a new list
        //check each value against the new list as added
        //if a value is repeated do not add it
        //return the new list

    //findCompliment method

    //listSubsets method
        //method to return a string of individual subsets from a given set
        //should always include the empty set
        //dont need this for formal project

    //isProperSubset method
        //dont need this for formal project

    //091823: checking to test push and commits - it worked! had to set new remote URL

    //findPermutations method
    //accepts a list, and number of elements in a permutation
    //returns a list of each possible permutation of elements from the set

    //findCombinations method
    //accepts a list and number of elements in a combination
    //returns a list of each possible combination of elements from the set
    //note this differs from permutations; combinations can contain repeated elements

    //totalProbability method
    
    //conditionalProbability method

    //bayesTheorem method

    //independentVsDependent
}