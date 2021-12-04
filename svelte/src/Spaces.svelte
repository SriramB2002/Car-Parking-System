<script>
    let space_input = 0;
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
    }

    let space = "";
    let slots = 0;
    let promise = getSpaces();
    let choice;
</script>


<main>
    <p>Parking space:</p>
    {#await promise}
            <p>Loading spaces...</p>
        {:then spaces} 
        <select bind:value={choice} required>
            <option></option>
            {#each spaces as elem}
                <option>{elem}</option>
            {/each}
        </select>
            
        {/await}
        <br><br>
    <button on:click={tog}>Add Parking Space</button>
    <button>Delete Parking Space</button>
    <br><br>

    {#if space_input}
        <form on:submit|preventDefault={addSpace}>
            <p>Name of Parking Space: <input type="text" placeholder="Space" required bind:value={space}></p>
            <p>No.of Parking Slots: <input type="number" placeholder="Number of Slots" required bind:value={slots}></p>
            <button type="submit">Update</button>
        </form>
    {/if}
</main>