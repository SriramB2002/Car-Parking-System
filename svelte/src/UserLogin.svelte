<svelte:head>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</svelte:head>

<script>
    import { Router, navigate } from "svelte-navigator";
    let username = 'user', password = 'pwd';
    let ans = null;
    const nav1 = () => {
        navigate("UserRegister");
    }
    const nav2 = () => {
        navigate("http://localhost:8080/oauth2/authorization/google")
    }
    const nav3 = () => {
        navigate("http://localhost:8080/oauth2/authorization/facebook")
    }

    async function post() {
        const res = await fetch("http://localhost:8080/data", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                "user": username,
                "pass": password
            })
        })
        const resp = await res.text();
        console.log(resp);
    }
</script>

<Router>
    <main>
        <h2>User Login</h2>
        <div class="enter">
            <h3>Enter login credentials here:</h3>
            <form>
                <input type="text" placeholder="Username" bind:value={username} required><br>
                <input type="password" placeholder="Password" bind:value={password} required><br>
            </form>
            <button on:click={post}>Login</button>
            <p>(or)</p>
            <div class="g-signin2" data-longtitle="true" data-onsuccess="onSignIn"></div>
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

    .g-signin2 {
        margin-left: 144px;
    }
</style>