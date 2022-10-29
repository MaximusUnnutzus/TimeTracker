<script>
    import axios from "axios";

    export let params;

    let id;

    $:{
        id = params.id
    }

    async function getEntrie(){

    }


</script>

<table class="table table-hover">
    <thead>
      <tr>
        <th scope="col">Date</th>
        <th scope="col">From</th>
        <th scope="col">Until</th>
        <th scope="col">Time</th>
        <th scope="col">Comment</th>
        <th scope="col">Type</th>
        <th scope="col">State</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>Otto</td>
        <td>@mdo</td>
      </tr>
    </tbody>
  </table>


  <style>
    .table{

    }
  </style>