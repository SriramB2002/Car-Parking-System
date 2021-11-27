<script>
    import { Router, navigate } from "svelte-navigator";
    const navy = () => {
        navigate("WorkerDashboard");
    }
</script>

<Router>
    <main>
        <h2>Worker Login</h2>
        <div class="enter">
            <h3>Enter login credentials here:</h3>
            <form>
                <input type="text" placeholder="Username" required><br>
                <input type="password" placeholder="Password" required><br>
            </form>
            <button on:click={navy}>Login</button>
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