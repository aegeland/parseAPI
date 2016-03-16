package honr499app.parseAPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import org.parse4j.ParseObject;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {

	private DataHandler handleData;
	private JFrame frmParseDataGenerator;
	private final double lat_min = -90.0000000;
	private final double lat_value = 0.0000000;
	private final double lat_max = 90.0000000;
	private final double lat_stepSize = 0.0000001;
	private final double lng_min = -180.0000000;
	private final double lng_value = 0.0000000;
	private final double lng_max = 180.0000000;
	private final double lng_stepSize = 0.0000001;
	private final double lat_vari_min = 0.0000000;
	private final double lat_vari_value = 0.0000000;
	private final double lat_vari_max = 1.0000000;
	private final double lat_vari_stepSize = 0.0000001;
	private final double lng_vari_min = 0.0000000;
	private final double lng_vari_value = 0.0000000;
	private final double lng_vari_max = 1.0000000;
	private final double lng_vari_stepSize = 0.0000001;
	private final double space_min = 0.0000001;
	private final double space_value = 0.0000010;
	private final double space_max = 1.0000000;
	private final double space_stepSize = 0.0000001;
	private final double temp_min = -50.0;
	private final double temp_value = 70.0;
	private final double temp_max = 120.0;
	private final double temp_stepSize = 0.1;
	private final double temp_min_m = -50.0;
	private final double temp_value_m = 70.0;
	private final double temp_max_m = 120.0;
	private final double temp_stepSize_m = 00.1;
	private final double temp_vari_min_m = 00.0;
	private final double temp_vari_value_m = 00.0;
	private final double temp_vari_max_m = 05.0;
	private final double temp_vari_stepSize_m = 0.1;
	private SpinnerNumberModel lat_model = new SpinnerNumberModel(lat_value, lat_min, lat_max, lat_stepSize);
	private	SpinnerNumberModel lng_model = new SpinnerNumberModel(lng_value, lng_min, lng_max, lng_stepSize);
	private SpinnerNumberModel lat_model_m = new SpinnerNumberModel(lat_value, lat_min, lat_max, lat_stepSize);
	private	SpinnerNumberModel lng_model_m = new SpinnerNumberModel(lng_value, lng_min, lng_max, lng_stepSize);
	private	SpinnerNumberModel temp_model = new SpinnerNumberModel(temp_value, temp_min, temp_max, temp_stepSize);
	private	SpinnerNumberModel temp_model_m = new SpinnerNumberModel(temp_value_m, temp_min_m, temp_max_m, temp_stepSize_m);
	private	SpinnerNumberModel temp_vari_model_m = new SpinnerNumberModel(temp_vari_value_m, temp_vari_min_m, temp_vari_max_m, temp_vari_stepSize_m);
	private SpinnerNumberModel space_model_m = new SpinnerNumberModel(space_value, space_min, space_max, space_stepSize);
	private SpinnerNumberModel lat_vari_model_m = new SpinnerNumberModel(lat_vari_value, lat_vari_min, lat_vari_max, lat_vari_stepSize);
	private SpinnerNumberModel lng_vari_model_m = new SpinnerNumberModel(lng_vari_value, lng_vari_min, lng_vari_max, lng_vari_stepSize);
	private JSpinner temp_spinner;
	private JSpinner lat_spinner;
	private JSpinner lng_spinner;
	private JComboBox comboBoxUserID;
	private DefaultListModel lm;
	private JList listPoints;
	private JLabel lblPoints;
	private JSpinner lat_spinner_m;
	private JSpinner lng_spinner_m;
	private JComboBox<Direction> plotDirectionComboBox;
	private JSpinner space_spinner_m;
	private JSpinner latitude_variation_spinner_m;
	private JSpinner longitude_variation_spinner_m;
	private JSpinner temp_spinner_m;
	private JSpinner temp_variation_spinner_m;
	private JSpinner spinner_num_points_m;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.frmParseDataGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		handleData = new DataHandler();
		handleData.retrieveUsers();
		frmParseDataGenerator = new JFrame();
		frmParseDataGenerator.setTitle("Parse Data Generator");
		frmParseDataGenerator.setBounds(100, 100, 327, 380);
		frmParseDataGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmParseDataGenerator.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmRefreshData = new JMenuItem("Refresh Data");
		mnFile.add(mntmRefreshData);
		mnFile.add(mntmExit);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmParseDataGenerator.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelSinglePoint = new JPanel();
		tabbedPane.addTab("Single Point", null, panelSinglePoint, null);
		GridBagLayout gbl_panelSinglePoint = new GridBagLayout();
		gbl_panelSinglePoint.columnWidths = new int[]{0, 0, 0};
		gbl_panelSinglePoint.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSinglePoint.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelSinglePoint.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelSinglePoint.setLayout(gbl_panelSinglePoint);

		JLabel lblUserId = new JLabel("User ID:");
		GridBagConstraints gbc_lblUserId = new GridBagConstraints();
		gbc_lblUserId.anchor = GridBagConstraints.WEST;
		gbc_lblUserId.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId.gridx = 0;
		gbc_lblUserId.gridy = 0;
		panelSinglePoint.add(lblUserId, gbc_lblUserId);

		comboBoxUserID = new JComboBox(handleData.getUsers().toArray());
		GridBagConstraints gbc_comboBoxUserID = new GridBagConstraints();
		gbc_comboBoxUserID.gridwidth = 2;
		gbc_comboBoxUserID.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUserID.gridx = 0;
		gbc_comboBoxUserID.gridy = 1;
		panelSinglePoint.add(comboBoxUserID, gbc_comboBoxUserID);

		JLabel lblLatitude = new JLabel("Latitude:");
		GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
		gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitude.anchor = GridBagConstraints.WEST;
		gbc_lblLatitude.gridx = 0;
		gbc_lblLatitude.gridy = 2;
		panelSinglePoint.add(lblLatitude, gbc_lblLatitude);

		JLabel lblLongitude = new JLabel("Longitude:");
		GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
		gbc_lblLongitude.anchor = GridBagConstraints.WEST;
		gbc_lblLongitude.insets = new Insets(0, 0, 5, 0);
		gbc_lblLongitude.gridx = 1;
		gbc_lblLongitude.gridy = 2;
		panelSinglePoint.add(lblLongitude, gbc_lblLongitude);


		lat_spinner = new JSpinner(lat_model);
		JSpinner.NumberEditor lat_editor = (JSpinner.NumberEditor)lat_spinner.getEditor();
		DecimalFormat lat_format = lat_editor.getFormat();
		lat_format.setMinimumFractionDigits(7);
		GridBagConstraints gbc_lat_spinner = new GridBagConstraints();
		gbc_lat_spinner.weightx = 50.0;
		gbc_lat_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_lat_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_lat_spinner.gridx = 0;
		gbc_lat_spinner.gridy = 3;
		panelSinglePoint.add(lat_spinner, gbc_lat_spinner);

		lng_spinner = new JSpinner(lng_model);
		JSpinner.NumberEditor lng_editor = (JSpinner.NumberEditor)lng_spinner.getEditor();
		DecimalFormat lng_format = lng_editor.getFormat();
		lng_format.setMinimumFractionDigits(7);
		GridBagConstraints gbc_lng_spinner = new GridBagConstraints();
		gbc_lng_spinner.weightx = 50.0;
		gbc_lng_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_lng_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_lng_spinner.gridx = 1;
		gbc_lng_spinner.gridy = 3;
		panelSinglePoint.add(lng_spinner, gbc_lng_spinner);

		JLabel lblTime = new JLabel("Temperature:");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 4;
		panelSinglePoint.add(lblTime, gbc_lblTime);

		temp_spinner = new JSpinner(temp_model);
		JSpinner.NumberEditor temp_editor = (JSpinner.NumberEditor)temp_spinner.getEditor();
		DecimalFormat temp_format = temp_editor.getFormat();
		temp_format.setMinimumFractionDigits(1);
		GridBagConstraints gbc_temp_spinner = new GridBagConstraints();
		gbc_temp_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_temp_spinner.gridwidth = 2;
		gbc_temp_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_temp_spinner.gridx = 0;
		gbc_temp_spinner.gridy = 5;
		panelSinglePoint.add(temp_spinner, gbc_temp_spinner);

		JLabel lblPlaceHold = new JLabel("");
		GridBagConstraints gbc_lblPlaceHold = new GridBagConstraints();
		gbc_lblPlaceHold.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlaceHold.gridx = 1;
		gbc_lblPlaceHold.gridy = 6;
		panelSinglePoint.add(lblPlaceHold, gbc_lblPlaceHold);

		JButton btnPlot = new JButton("Plot Point");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleData.plotPoint(comboBoxUserID.getSelectedItem().toString(), lat_spinner.getValue().toString(), lng_spinner.getValue().toString(), temp_spinner.getValue().toString());
				//System.out.println(comboBoxUserID.getSelectedItem().toString() + " | " + Double.parseDouble(lat_spinner.getValue().toString()) + " | " + Double.parseDouble(lng_spinner.getValue().toString()) + " | " + temp_spinner.getValue().toString());
			}
		});
		GridBagConstraints gbc_btnPlot = new GridBagConstraints();
		gbc_btnPlot.anchor = GridBagConstraints.EAST;
		gbc_btnPlot.gridx = 1;
		gbc_btnPlot.gridy = 7;
		panelSinglePoint.add(btnPlot, gbc_btnPlot);

		JPanel panelMultiplePoints = new JPanel();
		tabbedPane.addTab("Multiple Points", null, panelMultiplePoints, null);
		GridBagLayout gbl_panelMultiplePoints = new GridBagLayout();
		gbl_panelMultiplePoints.columnWidths = new int[]{0, 0, 0};
		gbl_panelMultiplePoints.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelMultiplePoints.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelMultiplePoints.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelMultiplePoints.setLayout(gbl_panelMultiplePoints);

		JLabel lblUserId_1 = new JLabel("User ID:");
		GridBagConstraints gbc_lblUserId_1 = new GridBagConstraints();
		gbc_lblUserId_1.anchor = GridBagConstraints.WEST;
		gbc_lblUserId_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId_1.gridx = 0;
		gbc_lblUserId_1.gridy = 0;
		panelMultiplePoints.add(lblUserId_1, gbc_lblUserId_1);

		JComboBox comboBox = new JComboBox(handleData.getUsers().toArray());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panelMultiplePoints.add(comboBox, gbc_comboBox);

		JLabel lblNumberOfPoints = new JLabel("Number of Points:");
		GridBagConstraints gbc_lblNumberOfPoints = new GridBagConstraints();
		gbc_lblNumberOfPoints.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfPoints.gridx = 0;
		gbc_lblNumberOfPoints.gridy = 2;
		panelMultiplePoints.add(lblNumberOfPoints, gbc_lblNumberOfPoints);

		spinner_num_points_m = new JSpinner();
		spinner_num_points_m.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		GridBagConstraints gbc_spinner_num_points_m = new GridBagConstraints();
		gbc_spinner_num_points_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_num_points_m.gridwidth = 2;
		gbc_spinner_num_points_m.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_num_points_m.gridx = 0;
		gbc_spinner_num_points_m.gridy = 3;
		panelMultiplePoints.add(spinner_num_points_m, gbc_spinner_num_points_m);

		JLabel lblStartingLatitude = new JLabel("Starting Latitude:");
		GridBagConstraints gbc_lblStartingLatitude = new GridBagConstraints();
		gbc_lblStartingLatitude.anchor = GridBagConstraints.WEST;
		gbc_lblStartingLatitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartingLatitude.gridx = 0;
		gbc_lblStartingLatitude.gridy = 4;
		panelMultiplePoints.add(lblStartingLatitude, gbc_lblStartingLatitude);

		JLabel lblStartingLongitude = new JLabel("Starting Longitude:");
		GridBagConstraints gbc_lblStartingLongitude = new GridBagConstraints();
		gbc_lblStartingLongitude.anchor = GridBagConstraints.WEST;
		gbc_lblStartingLongitude.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartingLongitude.gridx = 1;
		gbc_lblStartingLongitude.gridy = 4;
		panelMultiplePoints.add(lblStartingLongitude, gbc_lblStartingLongitude);

		lat_spinner_m = new JSpinner(lat_model_m);
		JSpinner.NumberEditor lat_editor_m = (JSpinner.NumberEditor)lat_spinner_m.getEditor();
		DecimalFormat lat_format_m = lat_editor_m.getFormat();
		lat_format_m.setMinimumFractionDigits(7);
		GridBagConstraints gbc_lat_spinner_m = new GridBagConstraints();
		gbc_lat_spinner_m.weightx = 50.0;
		gbc_lat_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_lat_spinner_m.insets = new Insets(0, 0, 5, 5);
		gbc_lat_spinner_m.gridx = 0;
		gbc_lat_spinner_m.gridy = 5;
		panelMultiplePoints.add(lat_spinner_m, gbc_lat_spinner_m);

		lng_spinner_m = new JSpinner(lng_model_m);
		JSpinner.NumberEditor lng_editor_m = (JSpinner.NumberEditor)lng_spinner_m.getEditor();
		DecimalFormat lng_format_m = lng_editor_m.getFormat();
		lng_format_m.setMinimumFractionDigits(7);
		GridBagConstraints gbc_lng_spinner_m = new GridBagConstraints();
		gbc_lng_spinner_m.weightx = 50.0;
		gbc_lng_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_lng_spinner_m.insets = new Insets(0, 0, 5, 0);
		gbc_lng_spinner_m.gridx = 1;
		gbc_lng_spinner_m.gridy = 5;
		panelMultiplePoints.add(lng_spinner_m, gbc_lng_spinner_m);

		JLabel lblPlotInDirection = new JLabel("Plot In Direction:");
		GridBagConstraints gbc_lblPlotInDirection = new GridBagConstraints();
		gbc_lblPlotInDirection.anchor = GridBagConstraints.WEST;
		gbc_lblPlotInDirection.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlotInDirection.gridx = 0;
		gbc_lblPlotInDirection.gridy = 6;
		panelMultiplePoints.add(lblPlotInDirection, gbc_lblPlotInDirection);

		JLabel lblSpacingAmount = new JLabel("Spacing Amount:");
		GridBagConstraints gbc_lblSpacingAmount = new GridBagConstraints();
		gbc_lblSpacingAmount.anchor = GridBagConstraints.WEST;
		gbc_lblSpacingAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpacingAmount.gridx = 1;
		gbc_lblSpacingAmount.gridy = 6;
		panelMultiplePoints.add(lblSpacingAmount, gbc_lblSpacingAmount);

		plotDirectionComboBox = new JComboBox();
		plotDirectionComboBox.setModel(new DefaultComboBoxModel<>(Direction.values()));
		plotDirectionComboBox.setSelectedIndex(0);
		GridBagConstraints gbc_plotDirectionComboBox = new GridBagConstraints();
		gbc_plotDirectionComboBox.weightx = 50.0;
		gbc_plotDirectionComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_plotDirectionComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_plotDirectionComboBox.gridx = 0;
		gbc_plotDirectionComboBox.gridy = 7;
		panelMultiplePoints.add(plotDirectionComboBox, gbc_plotDirectionComboBox);

		space_spinner_m = new JSpinner(space_model_m);
		JSpinner.NumberEditor space_editor_m = (JSpinner.NumberEditor)space_spinner_m.getEditor();
		DecimalFormat space_format_m = space_editor_m.getFormat();
		space_format_m.setMinimumFractionDigits(7);
		GridBagConstraints gbc_space_spinner_m = new GridBagConstraints();
		gbc_space_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_space_spinner_m.insets = new Insets(0, 0, 5, 0);
		gbc_space_spinner_m.gridx = 1;
		gbc_space_spinner_m.gridy = 7;
		panelMultiplePoints.add(space_spinner_m, gbc_space_spinner_m);

		JLabel lblLatitudeVariation = new JLabel("Latitude Variation:");
		GridBagConstraints gbc_lblLatitudeVariation = new GridBagConstraints();
		gbc_lblLatitudeVariation.anchor = GridBagConstraints.WEST;
		gbc_lblLatitudeVariation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitudeVariation.gridx = 0;
		gbc_lblLatitudeVariation.gridy = 8;
		panelMultiplePoints.add(lblLatitudeVariation, gbc_lblLatitudeVariation);

		JLabel lblLongitudeVariation = new JLabel("Longitude Variation:");
		GridBagConstraints gbc_lblLongitudeVariation = new GridBagConstraints();
		gbc_lblLongitudeVariation.anchor = GridBagConstraints.WEST;
		gbc_lblLongitudeVariation.insets = new Insets(0, 0, 5, 0);
		gbc_lblLongitudeVariation.gridx = 1;
		gbc_lblLongitudeVariation.gridy = 8;
		panelMultiplePoints.add(lblLongitudeVariation, gbc_lblLongitudeVariation);

		latitude_variation_spinner_m = new JSpinner(lat_vari_model_m);
		JSpinner.NumberEditor lat_vari_editor_m = (JSpinner.NumberEditor)latitude_variation_spinner_m.getEditor();
		DecimalFormat lat_vari_format_m = lat_vari_editor_m.getFormat();
		lat_vari_format_m.setMinimumFractionDigits(7);
		GridBagConstraints gbc_latitude_variation_spinner_m = new GridBagConstraints();
		gbc_latitude_variation_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_latitude_variation_spinner_m.insets = new Insets(0, 0, 5, 5);
		gbc_latitude_variation_spinner_m.gridx = 0;
		gbc_latitude_variation_spinner_m.gridy = 9;
		panelMultiplePoints.add(latitude_variation_spinner_m, gbc_latitude_variation_spinner_m);

		longitude_variation_spinner_m = new JSpinner(lng_vari_model_m);
		JSpinner.NumberEditor lng_vari_editor_m = (JSpinner.NumberEditor)longitude_variation_spinner_m.getEditor();
		DecimalFormat lng_vari_format_m = lng_vari_editor_m.getFormat();
		lng_vari_format_m.setMinimumFractionDigits(7);
		GridBagConstraints gbc_longitude_variation_spinner_m = new GridBagConstraints();
		gbc_longitude_variation_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_longitude_variation_spinner_m.insets = new Insets(0, 0, 5, 0);
		gbc_longitude_variation_spinner_m.gridx = 1;
		gbc_longitude_variation_spinner_m.gridy = 9;
		panelMultiplePoints.add(longitude_variation_spinner_m, gbc_longitude_variation_spinner_m);

		JLabel lblStartingTemperature = new JLabel("Starting Temperature:");
		GridBagConstraints gbc_lblStartingTemperature = new GridBagConstraints();
		gbc_lblStartingTemperature.anchor = GridBagConstraints.WEST;
		gbc_lblStartingTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartingTemperature.gridx = 0;
		gbc_lblStartingTemperature.gridy = 10;
		panelMultiplePoints.add(lblStartingTemperature, gbc_lblStartingTemperature);

		JLabel lblMaxTempVariation = new JLabel("Max Temp Variation:");
		GridBagConstraints gbc_lblMaxTempVariation = new GridBagConstraints();
		gbc_lblMaxTempVariation.anchor = GridBagConstraints.WEST;
		gbc_lblMaxTempVariation.insets = new Insets(0, 0, 5, 0);
		gbc_lblMaxTempVariation.gridx = 1;
		gbc_lblMaxTempVariation.gridy = 10;
		panelMultiplePoints.add(lblMaxTempVariation, gbc_lblMaxTempVariation);

		temp_spinner_m = new JSpinner(temp_model_m);
		JSpinner.NumberEditor temp_editor_m = (JSpinner.NumberEditor)temp_spinner_m.getEditor();
		DecimalFormat temp_format_m = temp_editor_m.getFormat();
		temp_format_m.setMinimumFractionDigits(1);
		GridBagConstraints gbc_temp_spinner_m = new GridBagConstraints();
		gbc_temp_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_temp_spinner_m.insets = new Insets(0, 0, 5, 5);
		gbc_temp_spinner_m.gridx = 0;
		gbc_temp_spinner_m.gridy = 11;
		panelMultiplePoints.add(temp_spinner_m, gbc_temp_spinner_m);

		temp_variation_spinner_m = new JSpinner(temp_vari_model_m);
		JSpinner.NumberEditor temp_vari_editor_m = (JSpinner.NumberEditor)temp_variation_spinner_m.getEditor();
		DecimalFormat temp_vari_format_m = temp_vari_editor_m.getFormat();
		temp_vari_format_m.setMinimumFractionDigits(1);
		GridBagConstraints gbc_temp_variation_spinner_m = new GridBagConstraints();
		gbc_temp_variation_spinner_m.weightx = 50.0;
		gbc_temp_variation_spinner_m.insets = new Insets(0, 0, 5, 0);
		gbc_temp_variation_spinner_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_temp_variation_spinner_m.gridx = 1;
		gbc_temp_variation_spinner_m.gridy = 11;
		panelMultiplePoints.add(temp_variation_spinner_m, gbc_temp_variation_spinner_m);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 12;
		panelMultiplePoints.add(label, gbc_label);

		JButton btnPlotPoints = new JButton("Plot Points");
		btnPlotPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double lat_value = Double.parseDouble(lat_spinner_m.getValue().toString());
				double lng_value = Double.parseDouble(lng_spinner_m.getValue().toString());
				Enum<Direction> plotDirection = (Enum<Direction>) plotDirectionComboBox.getSelectedItem();
				double space = Double.parseDouble(space_spinner_m.getValue().toString());
				double lat_variation = Double.parseDouble(latitude_variation_spinner_m.getValue().toString());
				double lng_variation = Double.parseDouble(longitude_variation_spinner_m.getValue().toString());
				double temp = Double.parseDouble(temp_spinner_m.getValue().toString());
				double temp_variation = Double.parseDouble(temp_variation_spinner_m.getValue().toString());
				NumberFormat formatter = new DecimalFormat("#0.0000000");
				System.out.println(space);

				double incrementSpacing = 0;
				for(int a = 0; a < Integer.parseInt(spinner_num_points_m.getValue().toString()); a++){
					if(space < 0.0000001){
						break;
					}else{
						if(lat_variation != 0){
							Random r = new Random();
							double random_lat_val = (lat_value - lat_variation) + ((lat_value + lat_variation) - (lat_value - lat_variation)) * r.nextDouble();
							if(plotDirection.equals(Direction.NORTH)){
								random_lat_val = random_lat_val + incrementSpacing;
							}else if(plotDirection.equals(Direction.SOUTH)){
								random_lat_val = random_lat_val - incrementSpacing;
							}
							System.out.println(round(random_lat_val,7));
						}else{
							double lat_val = 0;
							if(plotDirection.equals(Direction.NORTH)){
								lat_val = lat_value + incrementSpacing;
							}else if(plotDirection.equals(Direction.SOUTH)){
								lat_val = lat_value - incrementSpacing;
							}else{
								lat_val = lat_value;
							}
							System.out.println(round(lat_val,7));
						}

						if(lng_variation != 0){
							Random r = new Random();
							double random_lng_val = (lng_value - lng_variation) + ((lng_value + lng_variation) - (lng_value - lng_variation)) * r.nextDouble();
							if(plotDirection.equals(Direction.EAST)){
								random_lng_val = random_lng_val + incrementSpacing;
							}else if(plotDirection.equals(Direction.WEST)){
								random_lng_val = random_lng_val - incrementSpacing;
							}
							System.out.println(round(random_lng_val,7));
						}else{
							double lng_val = 0;
							if(plotDirection.equals(Direction.EAST)){
								lng_val = lng_value + incrementSpacing;
							}else if(plotDirection.equals(Direction.WEST)){
								lng_val = lng_value - incrementSpacing;
							}else{
								lng_val = lng_value;
							}
							System.out.println(round(lng_val,7));
						}
					}
					incrementSpacing += space;
				}
				//handleData.plotPoint(comboBoxUserID.getSelectedItem().toString(), lat_spinner.getValue().toString(), lng_spinner.getValue().toString(), temp_spinner.getValue().toString());
			}
		});
		GridBagConstraints gbc_btnPlotPoints = new GridBagConstraints();
		gbc_btnPlotPoints.anchor = GridBagConstraints.EAST;
		gbc_btnPlotPoints.gridx = 1;
		gbc_btnPlotPoints.gridy = 13;
		panelMultiplePoints.add(btnPlotPoints, gbc_btnPlotPoints);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Edit Points", null, panel, null);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lm.removeAllElements();
				handleData.refresh();
				populateListModel();

			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnRefresh, 5, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnRefresh, 5, SpringLayout.WEST, panel);
		panel.add(btnRefresh);

		JButton btnRemoveSelected = new JButton("Remove Selected");
		btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleData.removePoint((ParseObject) listPoints.getSelectedValue());
				handleData.refresh();
				populateListModel();
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnRemoveSelected, 0, SpringLayout.NORTH, btnRefresh);
		sl_panel.putConstraint(SpringLayout.WEST, btnRemoveSelected, 5, SpringLayout.EAST, btnRefresh);
		panel.add(btnRemoveSelected);

		JScrollPane scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.SOUTH, btnRefresh);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, panel);
		panel.add(scrollPane);

		lblPoints = new JLabel("#Points:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPoints, 0, SpringLayout.NORTH, btnRefresh);
		sl_panel.putConstraint(SpringLayout.WEST, lblPoints, 5, SpringLayout.EAST, btnRemoveSelected);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblPoints, 0, SpringLayout.SOUTH, btnRefresh);
		panel.add(lblPoints);

		lm = new DefaultListModel();
		populateListModel();
		listPoints = new JList(lm);
		listPoints.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPoints.setCellRenderer(new PointListCellRenderer());
		scrollPane.setViewportView(listPoints);

	}

	private void populateListModel(){
		lm.removeAllElements();
		int i = 0;
		for(ParseObject o : handleData.getObjects()){
			lm.addElement(o);
			i++;
		}
		lblPoints.setText("#Points: " + i);

		//lm = new ListModel(handleData.getObjects());
	}

	private static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public class PointListCellRenderer extends JLabel implements ListCellRenderer {
		private Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

		public PointListCellRenderer() {
			setOpaque(true);
			setIconTextGap(12);
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			ParseObject po = (ParseObject) value;
			setText(po.getString("time").toString() + " @ " + po.getString("temp").toString() + " @ " + po.getParseGeoPoint("geo_point").getLatitude() + ", " + po.getParseGeoPoint("geo_point").getLongitude());
			//setIcon(entry.getImage());
			if (isSelected) {
				setBackground(HIGHLIGHT_COLOR);
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}
			return this;
		}
	}
}
