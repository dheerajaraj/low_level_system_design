package org.example;

import java.util.*;

/**
 * Part 1
 * We need to implement a method that records loans created by users and saves them to a datastore:
 *
 * def create_loan(loan_id, user_id, store, amount):
 * # TODO: Save the loan to datastore
 * Requirements: One of the inputs to this method is "store", which is the particular store a user shopped at, but in the Loan datastore, we need to save the topmost parent company for billing purposes.
 *
 * To figure out the parent company, we will be provided with a list of mappings of parent companies to their child store or stores. Each child store can only have one parent company. The topmost parent company may be multiple layers away. ex: Alphabet: Google, Gemini, Pixel
 *
 * You should assume that merchant_mapping_db and loan_creation_requests may change and you cannot modify those structures. So anytime create_loan is called with "Google", the Loan object saved should have "Alphabet" as merchant. Important: Because create_loan will be called frequently and in a latency-sensitive user path, we need it to be extremely quick, and run in constant time.
 *
 * starter code:
 *
 * merchant_mapping_db = {
 * "RetailPark": ["DonutLife", "WatchWater"]，
 * "MegaMart": ["QuickShop"，"DiscountDepot"]，
 * "ValueStore": ["OkayBuy"]
 * }
 *
 * @dataclass class Loan:
 *   loan_id: int
 *   user_id: str
 *   merchant: str
 *   amount: int
 *
 *   def __str __(self) -> str:
 *     return f"{self.loan_id} - {self.merchant} - {self.user_id} - {self.amount}"
 *
 * class AffirmCardLoanDB:
 *   def __init__(self) -> None:
 *     self. loans = []
 *
 *   def create_loan(self, loan_id, user_id, store, amount):
 *     loan = Loan(0, 'user','merchant', 12345)
 *     self.loans.append(Loan)
 *     print(loan)
 * Part 2
 * Match incoming transactions to loans based on following rules:
 *
 * Total matched transactions cannot exceed loan amount
 * Transaction userId must match loan User Id
 * If multiple loans match, use the one with lowest id
 * Transaction store must match loan store ( using merchant hierarchy)
 * Merchant hierarchy example:
 *
 * Alphabet: Google, Gemini
 * Google: Pixel, Nest
 * A "Pixel" transaction matches only "Pixel" loans, not "Nest" loans
 * Input format:
 *
 * txn-id, store-name, txn-amount, user-id
 * Example: 0,ShopWorld,100,user-1
 *
 * Output format:
 *
 * transaction {txn-id}, loan {loan-id}
 * Example: transaction 0, loan 0
 */
public class AffirmCardLoanDb {

    private class Loan {
        public int loanId;
        public String userId;
        public String merchant;
        public int amount;

        public Loan(int loanId, String userId, String merchant, int amount) {
            this.loanId = loanId;
            this.userId = userId;
            this.merchant = merchant;
            this.amount = amount;
        }
    }

    private class Transaction {
        public int txId;
        public int loanId;
        public String userId;
        public int amount;

        public Transaction(int txId, int loanId, String userId, int amount) {
            this.txId = txId;
            this.loanId = loanId;
            this.userId = userId;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }

    public HashMap<String, List<Loan>> loans;
    private final HashMap<String, HashSet<String>> merchantMap;
    private final HashMap<String, String> merchantToParentMap;
    private final HashMap<Integer, List<Transaction>> txMap;
    private static final String TRANSACTION_OUTPUT_FORMAT = "transaction %d, loan %d";

    public AffirmCardLoanDb() {
        this.loans = new HashMap<>();
        merchantMap = new HashMap<>(Map.of("RetailPark", new HashSet<>(), "Google", new HashSet<>(), "MegaMart", new HashSet<>()));
        merchantMap.get("RetailPark").addAll(Arrays.asList("WatchWater", "DonutLife"));
        merchantMap.get("Google").addAll(Arrays.asList("Alphabet"));
        merchantMap.get("MegaMart").addAll(Arrays.asList("QuickShop", "DiscountDepot"));
        merchantToParentMap = new HashMap<>();
        for (Map.Entry<String, HashSet<String>> entry : this.merchantMap.entrySet()) {
            HashSet<String> set = entry.getValue();
            set.forEach(item -> merchantToParentMap.put(item, entry.getKey()));
        }
        this.txMap = new HashMap<>();
    }

    public void createLoan(int loanId, String userId, String store, int amount) {
        String parentCompany = this.merchantToParentMap.getOrDefault(store, null);
        if (parentCompany == null) {
            throw new NoSuchElementException("Parent comapany does not exist for " + store);
        }
        Loan loan = new Loan(loanId, userId, parentCompany, amount);
        String key = parentCompany + userId;
        this.loans.putIfAbsent(key, new ArrayList<>());
        this.loans.get(key).add(loan);
    }

    public void addAndMatchTransaction(int txId, String storeName, String userId, int amount) {
        String parentCompany = this.merchantToParentMap.getOrDefault(storeName, null);
        if (parentCompany == null) {
            throw new NoSuchElementException("Parent comapany does not exist for " + parentCompany);
        }
        String key = parentCompany + userId;
        List<Loan> selLoans = this.loans.getOrDefault(key, new ArrayList<>());

        if (selLoans.isEmpty()) {
            throw new NoSuchElementException(String.format("Cannot find the loan to be matched for store name: %s and user id: %s", storeName, userId));
        }
        Loan selLoan = selLoans.getFirst();
        if (selLoan.userId.equals(userId)) {
            throw new NoSuchElementException(String.format("Cannot find the loan with userId: %s", userId));
        }
        int loanId = selLoan.loanId;
        this.txMap.putIfAbsent(loanId, new ArrayList<>());
        List<Transaction> txList = this.txMap.get(loanId);
        int txLoanAmount = txList.stream().mapToInt(Transaction::getAmount).reduce(0, (a, b) -> a + b);
        int finalLoanAmount = txLoanAmount + amount;
        if (finalLoanAmount > selLoan.amount) {
            throw new RuntimeException("Total tx amount cannot be greater than the loan amount");
        }
        Transaction tx = new Transaction(txId, loanId, userId, amount);
        txList.add(tx);
        System.out.println(String.format(TRANSACTION_OUTPUT_FORMAT, tx.txId, loanId));
    }


}
