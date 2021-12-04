<script>
    import { Table } from "sveltestrap/src";

    let username = "";
    let promise = query();

    const search = () => {
        promise = query();
    }
    
    async function query() {
        const res = await fetch("http://localhost:8080/search?user=" + username);
        const text = await res.json();
        console.log(text);
        return text;
    }
</script>


<main>
    <p>Find the user:</p>
    <input type="text" placeholder="Username" bind:value={username}>
    <button on:click={search}>Search</button>

    
        {#await promise}
        <p>Loading results...</p>
            
        {:then users}

        <Table>
            <thead>
                <th>Username</th>
                <th>Name</th>
                <th>Email</th>
                <th>Car No. Plate</th>
            </thead>
            {#each users as user}
            <tr>
                <td>{user.username}</td>
                <td>{user.first_name + " " + user.last_name}</td>
                <td>{user.email}</td>
                <td>{user.car_reg}</td>
            </tr>
            {/each} 
        </Table>
        {/await}
</main>