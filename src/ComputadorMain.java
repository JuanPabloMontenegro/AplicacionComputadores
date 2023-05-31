
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ComputadorMain extends JFrame {

    private List<Computador> listadoPC = new ArrayList<>();
    private JComboBox<String> procesadorComboBox;
    private JComboBox<String> graficaComboBox;
    private JComboBox<Integer> ramComboBox;
    private JTextArea listaPC;

    public ComputadorMain() {
        super("Listado de Computadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Procesador:"));
        procesadorComboBox = new JComboBox<>();
        procesadorComboBox.addItem("Intel i9");
        procesadorComboBox.addItem("Intel i7");
        procesadorComboBox.addItem("Intel i5");
        procesadorComboBox.addItem("Intel i3");
        procesadorComboBox.addItem("Ryzen 9");
        procesadorComboBox.addItem("Ryzen 7");
        procesadorComboBox.addItem("Ryzen 5");
        procesadorComboBox.addItem("Ryzen 3");
        inputPanel.add(procesadorComboBox);

        inputPanel.add(new JLabel("Gráfica:"));
        graficaComboBox = new JComboBox<>();
        graficaComboBox.addItem("NVIDIA RTX 3060");
        graficaComboBox.addItem("NVIDIA RTX 3060 Ti");
        graficaComboBox.addItem("NVIDIA RTX 3070");
        graficaComboBox.addItem("NVIDIA RTX 3080");
        graficaComboBox.addItem("NVIDIA RTX 3080 Ti");
        graficaComboBox.addItem("NVIDIA RTX 3090");
        graficaComboBox.addItem("NVIDIA RTX 4060");
        graficaComboBox.addItem("NVIDIA RTX 4070");
        graficaComboBox.addItem("NVIDIA RTX 4080");
        graficaComboBox.addItem("NVIDIA RTX 4090");
        graficaComboBox.addItem("AMD RX 5500");
        graficaComboBox.addItem("AMD RX 5600");
        graficaComboBox.addItem("AMD RX 5700");
        graficaComboBox.addItem("AMD RX 6500");
        graficaComboBox.addItem("AMD RX 6600");
        graficaComboBox.addItem("AMD RX 6700");
        graficaComboBox.addItem("Intel Graphics");
        graficaComboBox.addItem("AMD Radeon Graphics");
        inputPanel.add(graficaComboBox);

        inputPanel.add(new JLabel("RAM:"));
        ramComboBox = new JComboBox<>();
        ramComboBox.addItem(4);
        ramComboBox.addItem(8);
        ramComboBox.addItem(16);
        ramComboBox.addItem(32);
        ramComboBox.addItem(64);
        ramComboBox.addItem(120);
        inputPanel.add(ramComboBox);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String procesador = (String) procesadorComboBox.getSelectedItem();
                String grafica = (String) graficaComboBox.getSelectedItem();
                int ram = (int) ramComboBox.getSelectedItem();
                Computador pc = new Computador(procesador, grafica, ram);
                listadoPC.add(pc);
                actualizarListado();
            }
        });
        inputPanel.add(agregarButton);
        panel.add(inputPanel);

        listaPC = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(listaPC);
        panel.add(scrollPane);

        // Botones de ordenamiento
        JPanel ordenamientoPanel = new JPanel();
        JButton bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorRamBubbleSort();
            }
        });
        ordenamientoPanel.add(bubbleSortButton);

        JButton mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorRamMergeSort();
            }
        });
        ordenamientoPanel.add(mergeSortButton);

        JButton radixSortButton = new JButton("Radix Sort");
        radixSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorRamRadixSort();
            }
        });
        ordenamientoPanel.add(radixSortButton);

        JButton countingSortButton = new JButton("Counting Sort");
        countingSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorRamCountingSort();
            }
        });
        ordenamientoPanel.add(countingSortButton);

        panel.add(ordenamientoPanel);

        setContentPane(panel);
        pack();
        setVisible(true);
    }

    private void actualizarListado() {
        listaPC.setText("");
        for (Computador pc : listadoPC) {
            listaPC.append(pc.getProcesador() + ", " + pc.getGrafica() + ", " + pc.getRam() + " GB\n");
        }
    }

    private void ordenarPorRamBubbleSort() {
        int n = listadoPC.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listadoPC.get(j).getRam() < listadoPC.get(j + 1).getRam()) {
                    Computador temp = listadoPC.get(j);
                    listadoPC.set(j, listadoPC.get(j + 1));
                    listadoPC.set(j + 1, temp);
                }
            }
        }
        actualizarListado();
    }

    // Métodos de ordenamiento restantes
    private void ordenarPorRamMergeSort() {
        mergeSort(listadoPC, 0, listadoPC.size() - 1);
        actualizarListado();
    }

    private void mergeSort(List<Computador> list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);
            merge(list, left, middle, right);
        }
    }

    private void merge(List<Computador> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        List<Computador> leftList = new ArrayList<>();
        List<Computador> rightList = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(middle + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftList.get(i).getRam() >= rightList.get(j).getRam()) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    private void ordenarPorRamRadixSort() {
        int maxRam = getMaxRam();
        for (int exp = 1; maxRam / exp > 0; exp *= 10) {
            countingSortByDigit(exp);
        }
        actualizarListado();
    }

    private void countingSortByDigit(int exp) {
        int n = listadoPC.size();
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[(listadoPC.get(i).getRam() / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(listadoPC.get(i).getRam() / exp) % 10] - 1] = listadoPC.get(i).getRam();
            count[(listadoPC.get(i).getRam() / exp) % 10]--;
        }
        for (int i = 0; i < n; i++) {
            listadoPC.get(i).setRam(output[i]);
        }
    }

    private int getMaxRam() {
        int maxRam = 0;
        for (Computador pc : listadoPC) {
            if (pc.getRam() > maxRam) {
                maxRam = pc.getRam();
            }
        }
        return maxRam;
    }

    private void ordenarPorRamCountingSort() {
        int n = listadoPC.size();
        int[] output = new int[n];
        int[] count = new int[101];
        for (int i = 0; i < n; i++) {
            count[listadoPC.get(i).getRam()]++;
        }
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[listadoPC.get(i).getRam()] - 1] = listadoPC.get(i).getRam();
            count[listadoPC.get(i).getRam()]--;
        }
        for (int i = 0; i < n; i++) {
            listadoPC.get(i).setRam(output[i]);
        }
        actualizarListado();
    }

    public static void main(String[] args) {
        new ComputadorMain();
    }
}
