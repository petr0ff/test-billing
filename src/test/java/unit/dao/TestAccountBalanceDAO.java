package unit.dao;

import com.test.billing.dao.AccountBalanceDAO;
import com.test.billing.dao.AccountDAO;
import com.test.billing.dao.model.Account;
import com.test.billing.dao.model.AccountBalance;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by German on 24.09.2016.
 */
public class TestAccountBalanceDAO {

    static Map<Long, AccountBalance> accountBalances;
    static ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
    static AccountBalance accountBalance = new AccountBalance(123L, 124L, 125L, new Date(0L));

    @Mock
    static final AccountBalanceDAO accountBalanceDAO = mock(AccountBalanceDAO.class);

    @BeforeClass
    public static void initMock() {
        doAnswer(invocationOnMock -> {
            if (accountBalances == null) {
                accountBalances = new HashMap<>();
            }
            return null;
        }).when(accountBalanceDAO)
                .createAccountBalanceTable();

        doAnswer(invocationOnMock -> {
            if (accountBalances != null) {
                accountBalances.clear();
                accountBalances = null;
            }
            return null;
        }).when(accountBalanceDAO)
                .dropAccountBalanceTable();
    }

    @Test(priority = 0)
    public void createTable() {
        Assert.assertNull(accountBalances);
        accountBalanceDAO.createAccountBalanceTable();
        Assert.assertNotNull(accountBalances);
        Assert.assertTrue(accountBalances.isEmpty());
    }

    @Test(dependsOnMethods = "createTable")
    public void dropTable() {
        accountBalanceDAO.dropAccountBalanceTable();
        Assert.assertNull(accountBalances);
    }
}
