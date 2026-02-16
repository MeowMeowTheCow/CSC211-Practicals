from random import random, randint

def mcsOn3(X):
   n = len(X)
   maxsofar = 0
   for low in range(n):             # [0,n)
      for high in range(low, n):    # [low,n)
         sum = 0
         for r in range(low, high): # [low,high)
            sum += X[r]
            if (sum > maxsofar):
               maxsofar = sum
   return maxsofar

def mcsOn2A(X):
   n = len(X)
   maxsofar = 0
   for low in range(n):             # [0,n)
       sum = 0
       for r in range(low, n):   # [low,n)
          sum += X[r]
          if (sum > maxsofar):
             maxsofar = sum
   return maxsofar

def mcsOn2B(X):
   n = len(X)
   sumTo = [0]*(n+1)
   for i in range(n):               # [0,n)
      sumTo[i] = sumTo[i-1] + X[i]
   maxsofar = 0
   for low in range(n):             # [0,n)
       for high in range(low, n):   # [low,n)
          sum = sumTo[high] - sumTo[low-1]
          # sum of all elements in X[low..high)
          if (sum > maxsofar):
             maxsofar = sum
   return maxsofar

def maxStraddle(X, low, high):
    middle = (low + high) // 2
    sum = 0; maxsofarLeft = 0
    for i in range(low, middle+1):   # [low..middle]
       sum += X[middle - i + 1]
       if sum > maxsofarLeft:
          maxsofarLeft = sum
    sum = 0; maxsofarRight = 0
    for i in range(middle+1, high+1): # [middle+1..high]
       sum += X[i]
       if sum > maxsofarRight:
          maxsofarRight = sum
       return maxsofarLeft + maxsofarRight

def mcsOnlogn(X, low, high):
    if low > high:
       return 0
    if low == high:
       return max(0, X[low])
    middle = (low + high) // 2
    mLeft = mcsOnlogn(X, low, middle)
    mRight = mcsOnlogn(X, middle+1, high)
    mStraddle = maxStraddle (X, low, high)
    return max (mLeft, mRight, mStraddle)


def mcsOn(X):
    N = len(X)
    maxSoFar = 0.0
    maxToHere = 0.0
    for i in range(N):           # [1,N)
       maxToHere = max(maxToHere +X[i], 0.0)
       maxSoFar = max(maxSoFar, maxToHere)
    return maxSoFar

def main():
	n = 20 
	X = [randint(1,n)*(-1)**randint(2,4) for k in range(n)]
	countP = 0
	countM = 0
	for x in X:
		if x < 0:
			countM +=1
		else:
			countP +=1
	print(f"{countM = }  {countP = }")
	print(X)
	N = len(X)
	print(mcsOn3(X))
	print(mcsOn2A(X))
	print(mcsOn2B(X))
	print(mcsOn(X))
#	print(mcsOnlogn(X, 1, N))   




if __name__ == "__main__":
	main()
	print("-"*20)
