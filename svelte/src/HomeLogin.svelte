<script>
	import { Router, Link } from "svelte-navigator";
</script>

<Router>
	<main>
		<header><h1>Car Parking System</h1></header>
		<p>Access your login pages using the cards below</p>
		<div class="login">
			<div class="logincard">
				<p>Admin</p>
				<img src="https://cdn.dribbble.com/users/1068771/screenshots/14225432/media/0da8c461ba3920a8c827d864a6e051ed.jpg?compress=1&resize=400x300" alt="Admin" width="200px"><br>
				<Link to="AdminLogin"><button>Login</button></Link>
			</div>
			<div class="logincard">
				<p>Worker</p>
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZkBnt_UWUlIKpICWjbTVtdRVc5TM5gef12A&usqp=CAU" alt="Worker" width="200px" height="150px"><br>
				<Link to="WorkerLogin"><button>Login</button></Link>
			</div>
			<div class="logincard">
				<p>User</p>
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9HbugRUG253C14ERn39ncBwPvvZ9JgnJCnA&usqp=CAU" alt="User" width="200px"><br>
				<Link to="UserLogin"><button>Login</button></Link>
			</div>
		</div>
	</main>
</Router>

<style>
	main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}

    button {
        border-radius: 5px;
        border: 1px solid black;
    }

    p {
        font-size: large;
    }

    img {
        padding: 5px;
    }

	.login {
		display: flex;
		justify-content: center;
	}

	.logincard {
		padding: 10px;
		margin: 10px;
		border-radius: 10px;
		border: 2px solid black;
	}
</style>