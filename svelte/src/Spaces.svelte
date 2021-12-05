<script>
import { Table, Button } from "sveltestrap";

    let space_input = 0;
    let space = "";
    let slots = 0;
    let promise = getSpaces();
    let choice = "";
    let promise2 = getSlots();

    const tog = () => {
        space_input = 1;
    }

    async function getSpaces() {
        const res = await fetch("http://localhost:8080/getSpaces");
        const resp = await res.json();

        return resp;
    }

    async function addSpace() {
        const res = await fetch("http://localhost:8080/add?space=" + space + "&slots=" + slots, {
            method: 'POST',
            headers: {'content-type': 'application/json'}
        });
        const resp = await res.text();

        console.log(resp);
        promise = getSpaces();
        promise2 = getSlots();
        space_input = 0;
    }

    async function getSlots() {
        const res = await fetch("http://localhost:8080/getSlots?space=" + choice);
        const resp = await res.json();

        return resp;
    }

    const gog = () => {
        console.log("Hemlo saar");
    }

    function refresh() {
        promise2 = getSlots();
    }

</script>


<main>
    <p>Parking space:</p>
    {#await promise}
            <p>Loading spaces...</p>
        {:then spaces} 
        <select bind:value={choice} required on:change={refresh}>
            <option></option>
            {#each spaces as elem}
                <option value = {elem}>{elem}</option>
            {/each}
        </select>
            
        {/await}
        <br><br>
    <button on:click={tog}>Add Parking Space</button>
    <button disabled={choice == ""}>Delete Parking Space</button>
    <br><br>

    {#if space_input}
        <form on:submit|preventDefault={addSpace}>
            <p>Name of Parking Space: <input type="text" placeholder="Space" required bind:value={space}></p>
            <p>No.of Parking Slots: <input type="number" placeholder="Number of Slots" required bind:value={slots}></p>
            <button type="submit">Update</button>
        </form>
    {/if}

    <Table bordered>
        <thead>
            <th>Slot</th>
            <th>Space</th>
            <th>Remove</th>
        </thead>
        {#await promise2}
        <p>PLEASE WAIT</p>
            
        {:then slots}
            {#each slots as s}
            <tr>
                <td>{"Slot " + s.id}</td>
                <td>{s.location}</td>
                <td><div><Button color="danger" on:click={gog}>Remove</Button></div></td>
            </tr>
            {/each} 
            
        {/await}
        
    </Table>

</main>