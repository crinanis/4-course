using System;
using System.Web.UI;

namespace WebForm
{
    public partial class _Default : Page
    {
        private SimplexProxy.SimplexWeb simplex;
        protected void Page_Load(object sender, EventArgs e)
        {
            simplex = new SimplexProxy.SimplexWeb();
        }

        protected void calc_sum(object sender, EventArgs e)
        {
            int x, y;
            if (int.TryParse(this.x.Text.ToString(), out x) && int.TryParse(this.y.Text.ToString(), out y))
            {
                result.Text = simplex.Add(x, y).ToString();
            }
            else
            {
                result.Text = "Error";
            }
        }
    }
}