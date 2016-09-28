package unit.dao;

import com.test.billing.dao.AccountDAO;
import com.test.billing.dao.model.Account;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by German on 24.09.2016.
 */
public class TestAccountDAO {

    static Map<Long, Account> accounts;
    static ArgumentCaptor<Long> accountIdArgument = ArgumentCaptor.forClass(Long.class);
    static Account account;

    @Mock
    static final AccountDAO accountDAOMock = mock(AccountDAO.class);

    @BeforeClass
    public static void initMock() {
        account = new Account(123456L, "John", "Mihalich", "Smith", new Date(0L));
        // Create table
        doAnswer(invocationOnMock -> {
            if (accounts == null) {
                accounts = new HashMap<>();
            }
            return null;
        }).when(accountDAOMock)
                .createAccountTable();

        // Drop table
        doAnswer(invocationOnMock -> {
            if (accounts != null) {
                accounts.clear();
                accounts = null;
            }
            return null;
        }).when(accountDAOMock)
                .dropAccountTable();

        // Insert new Account
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Account account = (Account) invocationOnMock.getArguments()[0];
                accounts.put(account.getAccountId(), account);
                return null;
            }
        }).when(accountDAOMock).insert(any(Account.class));

        // Get by Account Id
        when(accountDAOMock.getAccountById(accountIdArgument.capture())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        return accounts.get(accountIdArgument.getValue());
                    }
                });

        // Check Account Table
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return accounts.size();
            }
        }).when(accountDAOMock).checkAccountTable();

        // Update Account
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Account newAccount = (Account) invocationOnMock.getArguments()[0];
                accounts.replace(account.getAccountId(), newAccount);
                return null;
            }
        }).when(accountDAOMock).update(any(Account.class));

    }


    /**
     * Test-case: Create Accounts Table.
     * Step: Call createAccountTable() method
     * Result: Verify that table is created and there are 0 entries.
     */
    @Test(priority = 0, description = "Create accounts table")
    public void createTable() {
        Assert.assertNull(accounts);
        accountDAOMock.createAccountTable();
        Assert.assertNotNull(accounts);
        Assert.assertEquals(accountDAOMock.checkAccountTable(), 0);
    }

    /**
     * Test-case: Create account.
     * Step: Call insert() method with Account instance
     * Result: Verify that table's rows number is 1.
     */
    @Test(priority = 1, description = "Create account")
    public void insertValidAccount() {
        accountDAOMock.insert(account);
        Assert.assertEquals(accountDAOMock.checkAccountTable(), 1);
    }

    /**
     * Test-case: Retrieve account.
     * Step: Call getAccountById() method with Account ID of created Account instance
     * Result: Verify that retrieved account is correct.
     */
    @Test(priority = 2, description = "Retrieve valid account")
    public void getByValidAccountId() {
        Assert.assertEquals(accountDAOMock.getAccountById(account.getAccountId()), account);
        Assert.assertEquals(accountDAOMock.getAccountById(account.getAccountId()).getLastName(), "Smith");
    }

    /**
     * Test-case: Retrieve invalid account.
     * Step: Call getAccountById() method with invalid account id
     * Result: Verify that retrieved account is null.
     */
    @Test(priority = 3, description = "Retrieve invalid account")
    public void getByInvalidAccountId() {
        Assert.assertNull(accountDAOMock.getAccountById(1L));
    }

    /**
     * Test-case: Update account.
     * Step: Call update() method with new Account instance
     * Result: Verify that account is updated.
     * Step: Call getAccountById by previous account Id
     * Result: Verify that account is not found.
     */
    @Test(priority = 4, description = "Update account")
    public void updateAccount() {
        Account newAccount = new Account(123456L, "Alex", "Mihalich", "Petrov", new Date(2L));
        accountDAOMock.update(newAccount);
        Assert.assertEquals(accountDAOMock.getAccountById(newAccount.getAccountId()).getLastName(), "Petrov");
    }

    /**
     * Test-case: Drop Accounts Table.
     * Step: Call dropAccountTable() method
     * Result: Verify that table is deleted
     */
    @Test(dependsOnMethods = "createTable", description = "Drop Accounts Table")
    public void dropTable() {
        accountDAOMock.dropAccountTable();
        Assert.assertNull(accounts);
    }
}
