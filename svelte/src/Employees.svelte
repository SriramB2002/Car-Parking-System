<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { Table } from "sveltestrap/src";
    import RemoveButton from "./RemoveButton.svelte";
</script>

<main>
    <h6>Employees List</h6>
    <Table>
        <thead>
            <th>#</th>
            <th>Name</th>
            <th>Remove</th>
        </thead>
        <tr>
            <td>1</td>
            <td>Sriram Balasubramanian</td>
            <td><RemoveButton/></td>
        </tr>
    </Table>
    <button>Add Worker</button>
</main>

<style>
    main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}
</style>