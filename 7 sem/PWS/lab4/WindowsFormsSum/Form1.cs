using System.Windows.Forms;

namespace WindowsFormsSum
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, System.EventArgs e)
        {
            ServiceReference2.SimplexSoapClient client = new ServiceReference2.SimplexSoapClient();

            var s1 = new ServiceReference2.A();
            s1.s = textBox1.Text;
            s1.k = int.Parse(textBox3.Text);
            s1.f = float.Parse(textBox5.Text);

            var s2 = new ServiceReference2.A();
            s2.s = textBox2.Text;
            s2.k = int.Parse(textBox4.Text);
            s2.f = float.Parse(textBox6.Text);

            var result = client.Sum(s1, s2);

            textBox7.Text = "Результат: " + result.s + " " + result.k.ToString() + " " + result.f.ToString();
        }
    }
}
