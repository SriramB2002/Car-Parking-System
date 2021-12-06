<script>

    let f = "";
    let l = "";
    let u = "";
    let p = "";
    let c_p = "";
    let a = "";
    let e = "";
    let m = "";
    let c = "";
    let ver = false;

    async function register() {
        verify();
        if (ver)
        {
            const res = await fetch("http://localhost:8080/register", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                "first_name": f,
                "last_name": l,
                "username": u,
                "password": p,
                "address": a,
                "email": e,
                "mobile": m,
                "car_reg": c,
                    
            })
        })
        const resp = await res.text();
        console.log(resp);
        }
        else alert("Password and Confirm Password don't match");
    }

    const verify = () => {
        if (p == c_p) ver = true;
    } 

</script>

<main>
    <h2>User Registration Details</h2>
    <form class="enter" on:submit|preventDefault={register}>
        <label for="firstname">First Name:</label>
        <input type="text" placeholder="First Name" required bind:value={f}>
        <label for="lastname">Last Name:</label>
        <input type="text" placeholder="Last Name" required bind:value={l}>
        <label for="Username">Username:</label>
        <input type="text" placeholder="Username" required bind:value={u}>
        <label for="password">Password:</label>
        <input type="password" placeholder="Password" required bind:value={p}>
        <label for="password-confirm">Confirm Password:</label>
        <input type="password" placeholder="Confirm Password" required bind:value={c_p}>
        <label for="residential-address">Residential Address:</label>
        <input type="text" placeholder="Residential Address" required bind:value={a}>
        <label for="email">Email ID:</label>
        <input type="email" placeholder="Email ID" required bind:value={e}>
        <label for="mobile">Mobile Number:</label>
        <input type="tel" placeholder="Mobile Number" required bind:value={m}>
        <label for="car-reg-no">Car Registration Number:</label>
        <input type="text" placeholder="Car Registration Number" required bind:value={c}><br>
        <button type="submit">Register</button>
        <button>Verify Email ID</button>
        <button>Verify Mobile Number</button>
    </form>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}

    button {
        border-radius: 5px;
        border: 1px solid black;
    }

    label {
        padding-top: 5px;
        padding-bottom: 5px;
    }

    form {
        width: 600px;
    }

    .enter {
        margin: 10px;
        margin-left: 280px;
        border-radius: 5px;
        border: 2px solid black;
    }
</style>