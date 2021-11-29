<script>
    import { Router, navigate } from "svelte-navigator";
    import { admin } from "./stores";
    let username, password;

    let a;

    admin.subscribe(x => {
        a = x;
    });

    const nav = () => {
        if (username == "admin" && password == "adminpwd"){
             navigate("AdminDashboard");
             admin.set(1);
        }

        else {
            admin.set(2);
        }
    }
</script>

<Router>
    <main>
        <h2>Admin Login</h2>
        <div class="enter">
            <h3>Enter login credentials here:</h3>
            <form>
                <input type="text" placeholder="Username" required bind:value={username}><br>
                <input type="password" placeholder="Password" required bind:value={password}><br>
            </form>
            <button on:click={nav}>Login</button>
            {#if a == 2}
                <p class="red">Invalid Credentials!</p>
            {/if}
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

    .red {
        color: red;
    }
</style>