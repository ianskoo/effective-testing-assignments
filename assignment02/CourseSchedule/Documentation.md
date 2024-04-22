Task 1:
To achieve maximum coverage it is enough if we come up with a few "standard" cases. This means one number is duplicate once, or one number appears more than 2 times.

Task 2:
from the constraints we come up with the following contracts:

pre-condition:
the biggest number in the array has to be less than array.length
nums cannot be null
nums.length has to be at least 2
each number has to be between 0 and array.length -1

post-condition:
return type must be an integer
return value must be between 1 and array.length -1

Task 4:
Since it is hard to generate directed graphs without cycles (without being prone to errors in implementation) we generate a random list of edges, and add a cycle to it to check if the cycle is recognized.