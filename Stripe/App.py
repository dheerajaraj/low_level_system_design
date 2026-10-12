from ChatBillingCalculator import ChatBillingCalculator
def main():
    calc = ChatBillingCalculator()
    #data = ["userA,100,100,payg","userB,20000,10000,fixed","userB,25000,12000,fixed"]
    data = ["userA,100,100,payg", "userA,100,100,payg", "userA,20000,10000,fixed", "userA,100,100,fixed", "userB,100,100,payg"]
    result = calc.calculate_monthly_billing(data)
    print(result)



if __name__ == "__main__":
    main()