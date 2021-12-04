<script>
    let space_input = 0;
    const tog = () => {
        space_input = 1;
    }
</script>


<main>
    <p>Parking space:</p>
    <select>
        <option></option>
        <option>Entrance Gate</option>
        <option>Connaught Place</option>
        <option>Academics Block</option>
        <option>Student Activity Center</option>
    </select><br><br>
    <button on:click={tog}>Add Parking Space</button>
    <button>Delete Parking Space</button>
    <br><br>
    {#if space_input}
        <input type="text" placeholder="Space">
        <button>Update</button>
    {/if}
</main>