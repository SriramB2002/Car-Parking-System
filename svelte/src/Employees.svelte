<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { Table } from "sveltestrap/src";
    import { Button } from "sveltestrap/src";

    async function getList() {
        const res = await fetch("http://localhost:8080/getworkers");
        return res.json();
    }

    let promise = getList();

    async function remove(ID) {
        const res = await fetch("http://localhost:8080/delete?id=" + ID, {
            method: 'DELETE',
            headers: {'content-type': 'application/json'}
        })

        const resp = await res.text();
        console.log(resp);

        promise = getList();
        

    }

    let toggle = 0;
    let workerName = "";
 
    const tog = () => {
        toggle = 1;
    }
 
    async function reg() {
        const res = await fetch("http://localhost:8080/addworker", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                "name": workerName,
                "pass": "12345"
            })
        })

        const resp = await res.text();
        console.log(resp);

        promise = getList();

        toggle = 0;
    }
</script>

<main>
    <h6>Employees List</h6>
    <Table>
        <thead>
            <th>#</th>
            <th>Name</th>
        </thead>
        {#await promise}
        <p>PLEASE WAIT</p>
            
        {:then workers}
            {#each workers as w}
            <tr>
                <td>{w.id}</td>
                <td>{w.name}</td>
                <td><button on:click={remove(w.id)}>Remove</button></td>
            </tr>
            {/each} 
            
        {/await}
        
    </Table>
    <button on:click={tog}>Add Worker</button>
    {#if toggle}
        <p></p>
        <p>Enter Worker Name:</p>
        <input type="text" bind:value={workerName}><br><br>
        <button on:click={reg}>Register Worker</button>
    {/if}
</main>

<style>
    main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}
</style>