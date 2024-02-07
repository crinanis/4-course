using System;
using System.Drawing;
using System.Windows.Forms;
using Proxy;

namespace WindowsFormsProxy
{
    public partial class Form1 : Form
    {
        Proxy.Simplex Simplex = new Proxy.Simplex();
        public Form1()
        {
            InitializeComponent();
            Simplex = new Proxy.Simplex();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            int val1, val2;
            if (int.TryParse(textBox1.Text.ToString(), out val1) && int.TryParse(textBox2.Text.ToString(), out val2))
            {
                int result = Simplex.Add(val1, val2);
                textBox3.Text = result.ToString();
            }
            else
            {
                textBox3.Text = "Error";
            }
        }


        private void button2_Click(object sender, EventArgs e)
        {
            string val1 = textBox6.Text.ToString();
            double val2;
            if (double.TryParse(textBox5.Text.ToString(), out val2))
            {
                string result = Simplex.Concat(val1, val2);
                textBox4.Text = result.ToString();
            }
            else
            {
                textBox4.Text = "Error";
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            var msu1 = new A();
            msu1.s = textBox9.Text;
            msu1.k = int.Parse(textBox11.Text);
            msu1.f = float.Parse(textBox13.Text);

            var msu2 = new A();
            msu2.s = textBox8.Text;
            msu2.k = int.Parse(textBox10.Text);
            msu2.f = float.Parse(textBox12.Text);

            var result = Simplex.Sum(msu1, msu2);

            textBox7.Text = result.s;
            textBox14.Text = result.k.ToString();
            textBox15.Text = result.f.ToString();
        }
    }
}
