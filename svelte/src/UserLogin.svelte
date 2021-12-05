<svelte:head>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</svelte:head>

<script>
    import { Router, navigate } from "svelte-navigator";
    import { login } from "./stores.js";

    let username = "", password = "";
    let r;
    
    const nav1 = () => {
        navigate("UserRegister");
    }

    async function post() {
        const res = await fetch("http://localhost:8080/login?uname=" + username + "&pass=" + password)
        
        try {
            const resp = await res.json();
            login.set(resp); 
            navigate("UserDashboard");
        }

        catch(err) {
            r = "wrong";
        }

    }

    window.onSignIn = (googleUser) => {
        login.set({
                id: -5,
                first_name: googleUser.getBasicProfile().getName(),
                last_name: "",
                username: googleUser.getBasicProfile().getName(),
                password: googleUser.getBasicProfile().getId(),
                address: "",
                car_reg: "",
                mobile: "",
                email: googleUser.getBasicProfile().getEmail()
            });
        navigate("UserDashboard");
  };
    
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

            {#if r == "wrong"}
                <p class="red">Wrong username/password</p>
            {/if}
            
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

    .red {
        color: red;
    }
</style>