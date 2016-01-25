package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GenerateWindow {

	private Button generateButton;
	Shell generateshell;
	Text nameText,heightText,rowText,columnText;
	public GenerateWindow(Shell shell) {
		generateshell = new Shell(shell, SWT.TITLE | SWT.SYSTEM_MODAL | SWT.CLOSE | SWT.MAX);
		generateshell.setLayout(new GridLayout(2, false));
		generateshell.setSize(500, 200);

		generateshell.setText("mazE Generate Window");
		generateshell.setLayout(new GridLayout(2, false));

		Group dialogFieldsGroup = new Group(generateshell, SWT.SHADOW_ETCHED_IN);
		dialogFieldsGroup.setText("Maze Generate Window" + " Properties");
		dialogFieldsGroup.setLayout(new GridLayout(2, true));
		dialogFieldsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Label nameLabel = new Label(dialogFieldsGroup, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		nameLabel.setText("maze name :");
		nameText = new Text(dialogFieldsGroup, SWT.None);
		nameText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));

		Label dimensionLabel = new Label(dialogFieldsGroup, SWT.NONE);
		dimensionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		dimensionLabel.setText("dimension :");
		heightText = new Text(dialogFieldsGroup, SWT.None);
		heightText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));

		Label rowLabel = new Label(dialogFieldsGroup, SWT.NONE);
		rowLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		rowLabel.setText("rows :");
		rowText = new Text(dialogFieldsGroup, SWT.None);
		rowText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));

		Label columnLabel = new Label(dialogFieldsGroup, SWT.NONE);
		columnLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		columnLabel.setText("column :");
		columnText = new Text(dialogFieldsGroup, SWT.None);
		columnText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));

		generateButton = new Button(dialogFieldsGroup, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		generateshell.setSize (300, 200);
		generateshell.open ();
	}

	public void setTriggerOk(SelectionListener listener)
	{
		generateButton.addSelectionListener(listener);
		
	}

}
