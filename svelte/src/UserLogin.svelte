<script>
    import { Router, navigate } from "svelte-navigator";
    import { login } from "./stores.js";

    let username = "", password = "";
    
    const nav1 = () => {
        navigate("UserRegister");
    }
    const nav2 = () => {
        //navigate("http://localhost:8080/oauth2/authorization/google")
    }
    const nav3 = () => {
        //navigate("http://localhost:8080/oauth2/authorization/facebook")
    }

    async function post() {
        const res = await fetch("http://localhost:8080/login", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        })
        const resp = await res.text();
        login.set(resp);
        console.log(resp);

        navigate("UserDashboard");
    }

    
</script>

<Router>
    <main>
        <h2>User Login</h2>
        <div class="enter">
            <h3>Enter login credentials here:</h3>
            <form on:submit|preventDefault={post}>
                <input type="text" placeholder="Username" required bind:value={username}><br>
                <input type="password" placeholder="Password" required bind:value={password}><br>
                <button type="submit">Login</button>
            </form>
            
            <p>(or)</p>
            <button on:click={nav2}>Google Login</button>
            <button on:click={nav3}>Facebook Login</button>
            <h3>Here for the first time?</h3> <button on:click={nav1}>Register</button>
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