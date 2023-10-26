package Project_1_Stats_Library;

import Project_1_Stats_Library.Custom_Exceptions.TotalProbabilityException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * The Stats Library Class is a custom class created to use in the Probability
 * and Applied Statistics course and beyond. The user can invoke its methods to
 * calculate several values associated with an object that represents a data set.
 * These methods can produce new sets, and inform the user on several key points of
 * data concerning a set or population. This data includes averages, variance,
 * standard deviation, and several forms of calculating probability of given events.
 * --
 * Author: Christian Simpson
 * Version: 10/13/23
 **/
public class StatsLibrary{

    /**
     * a helper method for summation of list elements.
     * @param anArrayList the list to sum values
     * @return the sum of list values.
     */
    public double findListSum(ArrayList<Double> anArrayList){
        double sum = 0;
        if(!anArrayList.isEmpty()) {
            for (Double d : anArrayList) {
                sum += d;
            }
        }
        return sum;
    }

    /**
     * subtracts the mean of values in a list from each of those values.
     * Used in calculating standard deviation of a set of values
     * @param anArrayList the list of values
     * @param mean mean of the passed list
     * @return the new list
     */
    public ArrayList<Double> findDifferenceOfValuesAndMean(ArrayList<Double> anArrayList, double mean){
        //create a list of equal size to hold new values
        ArrayList<Double> differenceList = new ArrayList<>(anArrayList.size());
        //for each value of the passed list,
        for (Double d : anArrayList){
            //set the elements of the new list as difference of passed list value and mean
            differenceList.add(d - mean);
        }
        //return the new list
        return differenceList;
    }

    /**
     * squares each value in a list.
     * @param anArrayList a list of values
     * @return a list of squared values
     */
    public ArrayList<Double> squareList(ArrayList<Double> anArrayList){
        for(Double d : anArrayList){
            double element = d;
            anArrayList.remove(d);
            element *= element;
            anArrayList.add(element);
        }
        return anArrayList;
    }
    /**
     * findMean computes the average of values in a list.
     * @param anArrayList the list to iterate
     * @return the average
     */
    public double findMean(ArrayList<Double> anArrayList){
        return findListSum(anArrayList) / anArrayList.size();
    }

    /**
     * sorts the passed list, and compares adjacent values to find the most
     * represented value in the list.
     * @param anArrayList a list of values
     * @return the most represented value in the list
     */
    public double findMode(ArrayList<Double> anArrayList){
        //sort the list
        Collections.sort(anArrayList);
        //loop list and compare pairs of values
        int count = 0, maxCount = 0;
        double mode = anArrayList.get(0);
        int i = 0, j = 1;
        do{
            //if equal increment count
            if(Objects.equals(anArrayList.get(i), anArrayList.get(j))){
                count++;
            }
            //else non equal is found
            //check if count is greater than current highest
            if(count > maxCount){
                //set mode value to this index element
                mode = anArrayList.get(i);
                //set current max count to count
                maxCount = count;
                //reset count
                count = 0;
            }
            i++;
            j++;
        }while(j < anArrayList.size());
        return mode;
    }

    /**
     * sorts the passed array, and finds the median by calculating the middle index.
     * if the list size is even, the median is taken as the average of the two middle
     * values.
     * @param anArrayList a list of values
     * @return the median of the values in the list
     */
    public double findMedian(ArrayList<Double> anArrayList){
        Collections.sort(anArrayList);
        double median;
        //if size is odd,
        if(anArrayList.size() % 2 != 0){
            //median is middle element
            median = anArrayList.get(anArrayList.size() / 2);
        }
        //else if size is even,
        else{
            int middle = (anArrayList.size() / 2);
            //median is average of adjacent middle elements
            median = ((anArrayList.get(middle) + anArrayList.get(middle - 1)) / 2.0);
        }
        return median;
    }

    /**
     * calculates the standard deviation of a set of values. uses several helper
     * methods for each step.
     * @param anArrayList a list of values
     * @return the standard deviation of the values.
     */
    public double computeStandardDeviation(ArrayList<Double> anArrayList) {
        //calculate variance
        double mean = calculateVariance(anArrayList);
        //take the square root of the variance
        //return the result
        return Math.sqrt(mean);
    }

    /**
     * calculates the variance of a population.
     * @param anArrayList the list of elements passed to the method is considered the population.
     * @return the variance of the population
     */
    public double calculateVariance(ArrayList<Double> anArrayList){
        //get sum of the list elements
        //divide to find mean
        double mean = findMean(anArrayList);
        //for each element, subtract the mean from it to get a new list of values
        anArrayList = findDifferenceOfValuesAndMean(anArrayList, mean);
        //square each value
        anArrayList = squareList(anArrayList);
        //get sum of the list elements
        //find the new mean
        return findMean(anArrayList);
    }

    /**
     * calculates the factorial representation of a number returning a BigInteger object.
     * This allows the method to avoid any overflow that would occur if using primitive
     * datatypes.
     * @param l the number to calculate
     * @return the factorial of the input
     */
    public BigInteger calculateFactorial(int l){
        //case for zero factorial
        if(l == 0){
            return BigInteger.ONE;
        }
        else {
            BigInteger result = BigInteger.valueOf(l);
            while (l > 1) {
                l--;
                result = result.multiply(BigInteger.valueOf(l));
            }
            return result;
        }
    }

    /**
     * sorts the passed list and compares each pair of adjacent elements, removing any
     * repeated values.
     * @param anArrayList the list to sort and sift for unique values.
     * @return the list of unique values.
     */
    public ArrayList<Double> sortAndKeepUniqueElements(ArrayList<Double> anArrayList){
        Collections.sort(anArrayList);
        int j;
        for(int i = 0; i < anArrayList.size() - 1; i++){
            j = i + 1;
            while(Objects.equals(anArrayList.get(i), anArrayList.get(j))) {
                anArrayList.remove(j);
                j++;
            }
        }
        return anArrayList;
    }

    /**
     * from two sets, finds each element which exists in both sets and returns this sorted list of items.
     * @param listA the first list
     * @param listB the second list
     * @return a list of elements which occur in both sets.
     */
    public ArrayList<Double> findIntersection(ArrayList<Double> listA, ArrayList<Double> listB){
        //create new list object to return
        ArrayList<Double> intersection = new ArrayList<>();
        //find same elements and add to new list
        for(Double first : listA){
            for(Double second : listB){
                if(first.equals(second)){
                    intersection.add(first);
                }
            }
        }
        //sort and remove repeat elements
        return sortAndKeepUniqueElements(intersection);
    }

    /**
     * from two sets, creates a new set of elements consisting of the elements of both sets.
     * @param listA the first set
     * @param listB the second set
     * @return the elements of both sets
     */
    public ArrayList<Double> findUnion(ArrayList<Double> listA, ArrayList<Double> listB){
        //create new list object to return
        ArrayList<Double> union = new ArrayList<>();
        //add each element of A to union
        for(Double first : listA){
            union.add(first);
        }
        boolean match;
        int index;
        //for each element in B,
        for(Double second : listB){
            match = false;
            index = 0;
            //compare to each element in union
            while(index < union.size() && !match) {
                if(!second.equals(union.get(index))){
                    index++;
                    //if end of listB is reached,
                    if(index == union.size()){
                        //no match found
                        //add this unique element to union
                        union.add(second);
                    }
                }
                else{
                    match = true;
                }
            }
        }
        //finally sort and return union
        Collections.sort(union);
        return union;
    }

    /**
     * calculates the three central tendency values of a space and provides a
     * String representation of the output.
     * @param anArrayList the values of our space in a list
     * @return the mean, median, and mode of the space.
     */
    public String centralTendency(ArrayList<Double> anArrayList){
        String result = "";
        //call mean, median, mode methods
        //output each value and add it to the string
        //format the output
        return result;
    }

    //findCompliment method
        //given a set S and a universal set P (population)
        //compliment is all elements of P that are not in S
        //example a hand of 5 cards and a deck of cards
        //the compliment of the hand of cards is the remaining cards in the deck.

    //listSubsets method
        //method to return a string of individual subsets from a given set
        //should always include the empty set
        //dont need this for formal project

    //isProperSubset method
        //dont need this for formal project

    /**
     * calculates
     * @param space the total number of elements in the sample space.
     * @param coefficients the groups of those elements to consider which equal the total.
     * @return the amount of
     */
    public BigInteger calcMultinomialCoefficients(int space, int[] coefficients){
        //calculate numerator
        BigInteger total = calculateFactorial(space);
        BigInteger denominator = BigInteger.ONE;
        //calculate denominator
        for (int coefficient : coefficients) {
            denominator = denominator.multiply(calculateFactorial(coefficient));
        }
        //return the division
        return total.divide(denominator);
    }

    /**
     * calculates the number of permutations of an amount of elements resulting
     * from a set of elements. Note that the order of elements matters, and
     * duplicates are not allowed. uses the calculateFactorial helper method to calculate
     * each section of the formula before returning the full formula with those values
     * plugged in.
     * @param setElements the number of elements in the set
     * @param choices the number of elements in each permutation
     * @return the total possible permutations in the set
     */
    public BigInteger findPermutations(int setElements, int choices){
        //calc factorial of set elements
        BigInteger elementsFactorial = calculateFactorial(setElements);
        //subtract number of choices from number of elements
        int difference = setElements - choices;
        //calc factorial of difference
        BigInteger elementsMinusChoices = calculateFactorial(difference);
        //plug all values into permutations formula n! / (n-r)!
        return elementsFactorial.divide(elementsMinusChoices);
    }

    /**
     * calculates the number of combinations resulting from a set of elements
     * and how many elements to include in each resulting combination. Note that
     * order of the elements does not matter and duplicates are allowed. Uses the
     * calculateFactorial helper method to calculate each section of the formula
     * before returning the full formula with those values plugged in.
     * @param setElements the number of elements in the set
     * @param choices the number of elements in each combination
     * @return the total possible combinations in the set
     */
    public BigInteger findCombinations(int setElements, int choices){
        //check n >= r >= 0
        //calc factorial of set elements
        BigInteger elementsFactorial = calculateFactorial(setElements);
        //calc factorial of choices in the combination
        BigInteger choicesFactorial = calculateFactorial(choices);
        //subtract number of choices from number of elements
        int difference = setElements - choices;
        //calc factorial of difference
        BigInteger elementsMinusChoices = calculateFactorial(difference);
        //plug all values into combinations formula n! / r!(n-r)!
        return elementsFactorial.divide(elementsMinusChoices.multiply(choicesFactorial));
    }

    //probability axioms
        // a. The probability of an event occurring cannot be less than zero. P(A) >= 0
        // b. The total probability of events occurring in a set must be 100%. P(S) = 1
        // c. Probability of pairwise exclusive events are the sum of the possibilities.
            // eg. rolling an odd number on a 1d6 = 1/6 + 1/6 + 1/6 = 3/6 or 1/2

    /**
     * represents the probability axiom: "The probability of an event occurring cannot be less than zero".
     * @return true if the probability is negative, false otherwise.
     */
    public boolean negativeAxiom(double probability){
        return probability < 0;
    }
    /**
     * represents the probability axiom: "The total probability of events occurring
     * in a set must equal exactly 100%".
     * @param probabilities the list of probabilities to consider.
     * @return true if the total equals 100%, false otherwise.
     */
    public boolean totalAxiom(ArrayList<Double> probabilities){
        return findListSum(probabilities) == 1.0;
    }

    //theorem of total Probability method
    //                    n
    //representing P(A) = E  P(A|Bi)P(Bi)
    //                   i=1
    //or, The Probability of all occurrences of an event A within a sample space is equal to
    //the sum from i to n of one occurrence of A given Bi occurred, times the probability of Bi.

    /**
     * calculates the total probability of an event A, or P(A), by using the Theorem of Total Probability.
     * The Theorem states: "The Probability of all occurrences of an event A within a sample space is
     * equal to the sum from i to n of one occurrence of A given Bi occurred, times the probability
     * of Bi". These sample space probabilities are subject to all the axioms of probability. --
     * The passed lists should be formatted so the first contains the probability of the desired event
     * within the respective sample space should that same index element be chosen from the event B list.
     * ex. eventA.get(i) is P(A|Bi).
     * @param eventA the list of events A, which have unique probabilities of occurring in their
     *               respective sample spaces.
     * @param eventB the list of events B, which have unique probabilities of occurring in their
     *               respective sample space and determine the probability of the respective event A.
     * @return the total probability of an event A in a sample space.
     */
    public double thmTotalProb(ArrayList<Double> eventA, ArrayList<Double> eventB) throws TotalProbabilityException {
        //amount of steps in the addition loop
        int bSize = eventB.size();
        //check that total prob of A = 1 and B = 1
        //if either total != 1,
        if(!totalAxiom(eventA) || !totalAxiom(eventB)){
            throw new TotalProbabilityException();
        }
        else {
            double result = 0;
            //for each event B,
            for(int i = 0; i < bSize; i++) {
                //multiply P(A|Bi) by P(Bi)
                result += eventA.get(i)*eventB.get(i);
            }
            return result;
        }
    }
    
    //conditionalProbability method
    // "B Given A" P(B|A)
    //the probability of an event given that another has occurred.
    //example choosing a red ball from a bag, when that bag has been chosen from a set of bags.
    //what is the probability of each event "A"
    //then what is the probability of each event "B"
    //multiply P(A) x P(B) = P(AnB)
    //divide by P(B)

    //bayesTheorem method
    //P(B|A) = P(A|B)P(B) / P(A)
    /* the probability of an event B, given an event A has occurred is...
    the probability of an event A, given an event B has occurred times the probability of an event B */
    //all over the probability of an event A
    //multiply P(A) x P(B) = P(AnB)
    //multiply P(AnB) x P(B)
    //divide by P(A)

    //independent Vs Dependent intersection
    //exclusive vs not exclusive union ( i programmed exclusive already )
    //independence or dependency
    //Probability of B vs B given A

    //probability mass function method

    /**
     * calculates the binomial distribution for a set of values. Useful when data deals
     * with individual, independent trials resulting in either success or failure only.
     * @param n the number of trials conducted
     * @param y the intended number of successes
     * @param p the probability of success occurring in one trial
     * @return the probability of Y successes in N trials.
     */
    public double binomialDistribution(int n, int y, double p){
        //calculate combinations for N choose Y
        BigInteger combinations = findCombinations(n, y);
        //calculate q
        double q = 1 - p;
        //raise p to y
        double pRaisedY = Math.pow(p, y);
        //raise q to y - 1
        double qRaised = Math.pow(q, (n-y));
        //return combinations * p^y * q^y-1
        return combinations.longValue() * pRaisedY * qRaised;
    }

    //binomialMean
    //1 over p where p is the probability of success of an event

    //binomialVariance

    /**
     * calculates the geometric distribution for a set of values. Useful when data deals
     * with individual, independent trials resulting in either success or failure only.
     * @param y the number of trials
     * @param p the probability of success
     * @return the chance of finding a success at the yth trial.
     */
    public double geometricDistribution(int y, double p){
        double qRaised = Math.pow(1 - p, y-1);
        return qRaised * p;
    }

    //hyper geometric distribution

    //negative geometric distribution
    //y = total trials
    //p = chance of success
    //r = intended number of successes
    // (y-1)C(r-1) * p^r * q^(y-r)

    /**
     * the lambda, used in Poisson Distribution calculations, is a representation
     * of not only the mean of the data, but also the variance. It is calculated by
     * dividing the total number of events by the number of units measured, such as
     * hours, miles, etc.
     * @param k the number of events occurring.
     * @param n the units of measurement, per unit.
     * @return the average number of events per unit of measurement.
     */
    public double poissonLambda(double k, double n){
        //events per unit
        //k's per n
        return k / n;
    }

    /**
     * calculates the Poisson Distribution of a desired event in a sample space. Useful when
     * calculating the number of occurrences on a per-unit basis, such as units of time, speed,
     * area, volume, etc. It should be noted that the larger the number of trials become, the
     * smaller the chance of success of the event becomes - or, the success rate is
     * directly dependent on the region size.
     * @param lambda the mean; also the variance.
     * @param trials the number of trials to conduct
     * @return the chance of success within the performed trials space.
     */
    public double poissonDistribution(double lambda, int trials){
        //lambda raised to trials times...
        double lambdaRaisedTrials = Math.pow(lambda, trials);
        //euler's number raised to the negative lambda...
        double eulerRaisedNegLambda = Math.pow(EULERS_NUMBER, Math.negateExact((long) lambda));
        //all over the number of trials factorial
        BigInteger bigTrials = calculateFactorial(trials);
        return (lambdaRaisedTrials * eulerRaisedNegLambda) / bigTrials.longValue();
    }

    //poisson compliment

    //standard deviation computation using binomial / geometric values

    //chebyshev's theorem

    //calculate "within" number method
    //used in chebyshev's

    private static final double EULERS_NUMBER = 2.7182818;

}
