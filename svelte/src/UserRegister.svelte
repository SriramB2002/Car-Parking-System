<script>

    // Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth, createUserWithEmailAndPassword, sendEmailVerification, signOut } from "firebase/auth";
import { navigate } from "svelte-navigator";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyA2ZtumeApFL36JuYweU7vVc_He62LefyQ",
  authDomain: "car-parking-system-86220.firebaseapp.com",
  projectId: "car-parking-system-86220",
  storageBucket: "car-parking-system-86220.appspot.com",
  messagingSenderId: "68714942531",
  appId: "1:68714942531:web:95dca785c45419a0c704f6"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

    import { m as mod } from "./stores";
    const auth = getAuth();
    signOut(auth);
    

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
    let emailverified = false;

    async function verifyEmail() {
        createUserWithEmailAndPassword(auth, e, p)
            .then((cred) => {
                sendEmailVerification(cred.user).then(() => {
                    console.log("SENT");
                })
            })
    }

    async function register() {
        verify();
        if (ver)
        {   
            auth.currentUser.reload();
            const user = auth.currentUser;
            if(user.emailVerified) {
                console.log("VERIFIED");
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
                navigate("UserLogin");
            }

            else {
                alert("EMAIL NOT VERIFIED");
            }

        }

        else alert("Password and Confirm Password don't match");

        mod.set("");
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
        <button on:click={verifyEmail}>Verify Email</button>
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
        margin-left: 410px;
        border-radius: 5px;
        border: 2px solid black;
    }
</style>