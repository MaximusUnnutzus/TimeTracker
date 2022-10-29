<script>
  import Chart from "chart.js/auto";
  import axios from "axios";

  let ctx;

  let menus = [
    {
      gerichtName: "",
      Preis: "",
      zutaten: [],
    },
  ];

  let anzahlZutaten = [];
  let namen = [];

  function readySet() {
    menus.forEach((menu) => {
      namen.push(menu.gerichtName);
      anzahlZutaten.push(menu.zutaten.length);
    });
  }

  async function getMenus() {
    axios.get("http://localhost:3001/api/menus").then((response) => {
      menus = response.data;
      createChart();
    });
  }

  getMenus();

  async function createChart() {
    readySet();
    const myChart = new Chart(ctx, {
      type: "bar",
      data: {
        labels: namen,
        datasets: [
          {
            label: "Anzahl Zutaten pro Gericht",
            data: anzahlZutaten,
            backgroundColor: ["rgba(	135, 206, 250, 0.8)"],
            borderWidth: 2,
            borderRadius: 10,
          },
        ],
      },
      options: {
        plugins: {
          legend: {
            labels: {
              font: {
                size: 20,
              },
            },
          },
        },
        scales: {
          y: {
            beginAtZero: true,

            grid: {
              color: "black",
            },
            ticks: {
              color: "black",
              font: {
                size: "16",
              },
              stepSize: 1,
            },
          },

          x: {
            grid: {
              color: "black",
            },
            ticks: {
              color: "black",
              font: {
                size: "16",
              },
            },
          },
        },
      },
    });
  }
</script>

<canvas id="myChart" width="400px" height="400px" bind:this={ctx} />
