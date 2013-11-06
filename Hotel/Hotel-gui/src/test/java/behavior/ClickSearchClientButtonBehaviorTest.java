package behavior;

import com.vaadin.ui.Table;
import common.tableBuilder.TableContent;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.cantor.CantorTableCreator;
import view.CantorPanel;
import view.PopUpComponent;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClickSearchClientButtonBehaviorTest {

    @Mock
    CantorPanel cantorPanelMock;

    @Mock
    CantorTableCreator creatorMock;

    @Mock
    PopUpComponent popUpComponentMock;

    ClickSearchClientButtonBehavior behavior;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        behavior = new ClickSearchClientButtonBehavior();
        behavior.setCantorPanel(cantorPanelMock);
        behavior.setCreator(creatorMock);
        behavior.setPopUp(popUpComponentMock);
    }

    @Test
    public void shouldShowPopWhenClientIdIsEmpty() {
        // given
        String emptyClientId = "";

        when(cantorPanelMock.getClientId()).thenReturn(emptyClientId);

        // when
        behavior.buttonClick(null);

        // then
        verify(popUpComponentMock).showError(anyString());
        verify(cantorPanelMock).getClientId();
        verifyNoMoreInteractions(cantorPanelMock);
    }

    @Test
    public void shouldShowPopWhenClientIdIsNotValidPeselOrKrs() {
        // given
        String incorrectClientId = "342";

        when(cantorPanelMock.getClientId()).thenReturn(incorrectClientId);

        // when
        behavior.buttonClick(null);

        // then
        verify(popUpComponentMock).showError(anyString());
        verify(cantorPanelMock, times(2)).getClientId();
        verifyNoMoreInteractions(cantorPanelMock);
    }

    @Test
    public void shouldRefreshClientTable() {
        // given

        long pesel = 41061407997L;
        String clientId = "41061407997";

        when(cantorPanelMock.getClientId()).thenReturn(clientId);

        when(creatorMock.createCustomerTable(pesel)).thenReturn(TableContent.EMPTY);

        // when
        behavior.buttonClick(null);

        // then
        verifyZeroInteractions(popUpComponentMock);
        verify(cantorPanelMock, times(3)).getClientId();
        verify(creatorMock).createCustomerTable(pesel);
        verify(cantorPanelMock).refreshCustomerTable(any(Table.class));
        verifyNoMoreInteractions(cantorPanelMock);
    }

    @Test
    public void shouldRefreshCompanyTable() {
        // given

        long krs = 385161L;
        String companyId = "0000385161";

        when(cantorPanelMock.getClientId()).thenReturn(companyId);

        when(creatorMock.createCompanyTable(krs)).thenReturn(TableContent.EMPTY);

        // when
        behavior.buttonClick(null);

        // then
        verifyZeroInteractions(popUpComponentMock);
        verify(cantorPanelMock, times(3)).getClientId();
        verify(creatorMock).createCompanyTable(krs);
        verify(cantorPanelMock).refreshCompanyTable(any(Table.class));
        verifyNoMoreInteractions(cantorPanelMock);
    }

}
