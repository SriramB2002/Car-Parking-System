<script>
    import { Router, navigate } from "svelte-navigator";
import { worker } from "./stores";

    let name = '', pass = '';

    async function post() {
        const res = await fetch("http://localhost:8080/checkin?name=" + name + "&pass=" + pass);
        const resp = await res.json();
        console.log(resp);
        worker.set(resp);

        navigate("WorkerDashboard");
    }
</script>

<Router>
    <main>
        <h2>Worker Login</h2>
        <div class="enter">
            <h3>Enter login credentials here:</h3>
            <form on:submit|preventDefault={post}>
                <input type="text" placeholder="Username" required bind:value={name}><br>
                <input type="password" placeholder="Password" required bind:value={pass}><br>
                <button type="submit">Login</button>
            </form>
        </div>
    </main>
</Router>

<style>
    main {
		text-align: center;
		padding: 1em;
        max-width: 480px;
		margin: 0 auto;
	}

    button {
        border-radius: 5px;
        border: 1px solid black;
    }

    .enter {
        margin: 10px;
        border-radius: 5px;
        border: 2px solid black;
    }
</style>