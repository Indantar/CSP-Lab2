import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



/**
 * Created by g00284823 on 17/09/2015.
 */
public class Lab1SwingWorker
{
    private JButton loadButton;
    private JPanel panel1;
    private JButton reverseContentButton;
    private JButton reverseWordsButton;
    private JButton countButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private String path;
    private String[] sentence;
    private String[] reverseSentence;
    private String[] reverseWordsSentence;

    public Lab1SwingWorker() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("I:\\Year 5\\CSP\\Lab1"));
                int res = fc.showOpenDialog(panel1);
                if( res == JFileChooser.APPROVE_OPTION)
                {}
                SwingWorker<Boolean, String> sw = new SwingWorker<Boolean, String>()
                {
                    @Override
                    protected Boolean doInBackground() throws Exception
                    {
                        try {
                            path = fc.getSelectedFile().getAbsolutePath();
                            File inputFile = fc.getSelectedFile();
                            FileReader fr = new FileReader(inputFile);
                            BufferedReader br = new BufferedReader(fr);
                            StringBuilder sb = new StringBuilder();
                            String s;
                            while ((s = br.readLine()) != null) {
                                sb.append(s+"\n");
                            }
                            String in = sb.toString();
                            String pub;
                            sentence = in.split("\n");
                            for(int  i = 0; i < sentence.length; i++)
                            {
                                pub = sentence[i] + "\n";
                                publish(pub);
                            }
                            fr.close();
                        } catch (FileNotFoundException e2) {
                            System.err.println("FileStreamsTest: " + e2);
                        } catch (IOException e1) {
                            System.err.println("FileStreamsTest: " + e1);
                        }
                        return true;
                    }
                    protected void process(List<String> s)
                    {
                        for(int i = 0; i < s.size();i++)
                            textArea1.append(s.get(i));
                    }
                };
                sw.execute();
            }
        });
        reverseContentButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               SwingWorker<Boolean, String> sw2 = new SwingWorker<Boolean, String>() {
                   @Override
                   protected Boolean doInBackground() throws Exception {
                       String pub;
                       StringBuilder sb = new StringBuilder();
                       String setUp = new String();
                       String[][] words = new String[sentence.length][];
                       for (int i = 0; i < sentence.length; i++)
                       {
                           words[i] = sentence[i].split("\\s+");
                       }
                       for (int j = 0; j < sentence.length; j++)
                       {
                           for (int k = words[j].length - 1; k >= 0; k--)
                           {
                               sb.append(words[j][k] + " ");
                               pub = words[j][k] + " ";
                               publish(pub);
                           }
                           sb.append("\n");
                           publish("\n");
                       }
                       setUp = sb.toString();
                       reverseSentence = new String[words.length];
                       reverseSentence = setUp.split("\n");
                       return true;
                   }
                   protected void process(List<String> s)
                   {
                       for(int i = 0; i < s.size();i++)
                           textArea2.append(s.get(i));
                   }
               };
               sw2.execute();
           }
       });
        reverseWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Boolean, String> sw3 = new SwingWorker<Boolean, String>()
                {
                    @Override
                    protected Boolean doInBackground() throws Exception
                    {
                        String pub;
                        StringBuilder sb = new StringBuilder();
                        String setUp = new String();
                        String[][] revWords = new String[sentence.length][];
                        for( int i = 0; i < sentence.length; i++)
                        {
                            revWords[i] = reverseSentence[i].split("\\s+");
                        }
                        for( int j = 0; j < sentence.length; j++)
                        {
                            for (int k = 0; k < revWords[j].length; k++)
                            {
                                if(revWords[j].length % 2 == 0)
                                {
                                    if(k % 2 == 0)
                                    {
                                        sb.append(revWords[j][k + 1] + " ");
                                        pub = revWords[j][k+1] + " ";
                                        publish(pub);
                                    }
                                    else
                                    {
                                        sb.append(revWords[j][k - 1] + " ");
                                        pub = revWords[j][k-1] + " ";
                                        publish(pub);
                                    }
                                }
                                else
                                {
                                    if(k % 2 == 0)
                                    {
                                        if(k+1 < revWords[j].length-1)
                                        {
                                            sb.append(revWords[j][k + 1] + " ");
                                            pub = revWords[j][k+1] + " ";
                                            publish(pub);
                                        }
                                        else
                                        {
                                            sb.append(revWords[j][k] + " ");
                                            pub = revWords[j][k] + " ";
                                            publish(pub);
                                        }
                                    }
                                    else
                                    {
                                        sb.append(revWords[j][k - 1] + " ");
                                        pub = revWords[j][k-1] + " ";
                                        publish(pub);
                                    }
                                }
                            }
                            publish("\n");
                        }
                        setUp = sb.toString();
                        reverseWordsSentence = new String[revWords.length];
                        reverseWordsSentence = setUp.split("\n");
                        return true;
                    }
                    protected void process(List<String> s)
                    {
                        for(int i = 0; i < s.size();i++)
                            textArea3.append(s.get(i));
                    }
                };
                sw3.execute();
            }
        });
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<String, String> sw4 = new SwingWorker<String, String>() {
                    @Override
                    protected String doInBackground() throws Exception
                    {
                        String out;
                        String word = new String();
                        String[][] words = new String[sentence.length][];
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < sentence.length; i++)
                        {
                            words[i] = sentence[i].replaceAll("[^a-zA-Z^]", " ").toLowerCase().split("\\s+");
                        }
                        HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
                        for (int j = 0; j < words.length; j++)
                        {
                            for (int l = 0; l < words[j].length; l++)
                            {
                                word = words[j][l];
                                if (wordCount.containsKey(word))
                                {
                                    wordCount.put(word, wordCount.get(word) + 1);
                                } else
                                {
                                    wordCount.put(word, 1);
                                }
                            }
                            Iterator i = wordCount.keySet().iterator();
                            while (i.hasNext())
                            {
                                String key = i.next().toString();
                                Integer val = wordCount.get(key);
                                sb.append(key + " " + val + "\n");
                            }
                        }
                        return sb.toString();
                    }
                    protected void done()
                    {
                        try
                        {
                            String s = get();
                            textArea4.append(s);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                };
                sw4.execute();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setContentPane(new Lab1SwingWorker().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}