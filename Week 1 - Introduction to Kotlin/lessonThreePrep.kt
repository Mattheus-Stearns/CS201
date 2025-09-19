class BankAccounts(var checking_balance: Double = 0.0, var savings_balance: Double = 0.0){
    // Deposits money into checking account
    fun depositChecking(amount: Double){
        checking_balance += amount
    }

    // Deposits money into the savings account.
    fun depositSavings(amount: Double){
        savings_balance += amount
    }

    // Transfers money from checking to savings
    fun transferFromCheckingToSavings(amount: Double){
        checking_balance -= amount
        savings_balance += amount
    }

    // Transfers money from savings to checking
    fun transferFromSavingsToChecking(amount: Double){
        savings_balance -= amount
        checking_balance += amount
    }

    // Gets the total amount stored in both accounts
    fun getTotalBalance(): Double{
        return savings_balance + checking_balance
    }
}

fun main(){
    val elephant = BankAccounts()
    
    //Deposit $20.00 and $15.50 into elephant's checking and savings account respectively
    elephant.depositChecking(20.00)
    elephant.depositSavings(15.50)
    
    //Transfer $18.10 from elephant's checking to savings
    elephant.transferFromCheckingToSavings(18.10)

    //Transfer $16.60 from elephant's savings to checking
    elephant.transferFromSavingsToChecking(16.60)

    println("Elephant checking amount = ${elephant.checking_balance}")

    println("Elephant savings amount = ${elephant.savings_balance}")
        
    println("Elephant total = ${elephant.getTotalBalance()}")
        
}

/* Excersize Two

    //Code Snippet A: Will throw a compiler error
    var course_number: String = "CS201"
    course_number = null

    //Code Snippet B
    var course_title: String? = "Data Structures"
    course_title = null

    //Code Snippet C: Will throw a compiler error
    var course_prof: String? = "Prof. Anya"
    val length = course_prof.length

    //Code Snippet D
    var course_department: String? = "Computer Science"
    val upper_case = course_department?.uppercase()
*/