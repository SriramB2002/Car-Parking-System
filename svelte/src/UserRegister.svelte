<script>

</script>

<main>
    <h2>User Registration Details</h2>
    <form class="enter">
        <label for="firstname">First Name:</label>
        <input type="text" placeholder="First Name" required>
        <label for="lastname">Last Name:</label>
        <input type="text" placeholder="Last Name" required>
        <label for="Username">Username:</label>
        <input type="text" placeholder="Username" required>
        <label for="password">Password:</label>
        <input type="password" placeholder="Password" required>
        <label for="password-confirm">Confirm Password:</label>
        <input type="password" placeholder="Confirm Password" required>
        <label for="residential-address">Residential Address:</label>
        <input type="text" placeholder="Residential Address" required>
        <label for="email">Email ID:</label>
        <input type="email" placeholder="Email ID" required>
        <label for="mobile">Mobile Number:</label>
        <input type="tel" placeholder="Mobile Number" required>
        <label for="car-reg-no">Car Registration Number:</label>
        <input type="text" placeholder="Car Registration Number" required><br>
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