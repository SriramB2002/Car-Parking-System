<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
    import { Navbar, NavbarBrand, Nav, NavItem, Modal, ModalHeader, ModalBody, ModalFooter, Button, Table } from "sveltestrap/src";
    import { login } from "./stores";

    const today = new Date();

	let date_str = [
		today.getFullYear(),
		pad(today.getMonth() + 1, 2),
		pad(today.getDate(), 2)
	].join('-');

    let time_1 = [
        today.getHours(),
        today.getMinutes()
    ].join(':');

    let time_2 = [
        today.getHours(),
        today.getMinutes()
    ].join(':');

	function pad(x, len) {
		x = String(x);
		while (x.length < len) x = `0${x}`;
		return x;
	}

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set({
                id: -1,
                first_name: "",
                last_name: "",
                username: "",
                password: "",
                address: "",
                car_reg: "",
                mobile: "",
                email: "",
                car_model: ""
            });
            navigate("UserLogin");
        });
    }
    
    const nav = () => {
        navigate("UserProfile");
    }

    let bool = false;
    function check() {
        bool = true;
        promise2 = search();
    }

    async function search() {
        const res = await fetch("http://localhost:8080/searchSlots", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                checkIn: time_1,
                checkOut: time_2,
                date: date_str,
                space: choice,
                model: $login.car_model
            })
        })

        const resp = await res.json();
        return resp; 
    }

    let bool1 = false;
    const openmodal = (x) => {
        bool1 = !bool1;
        console.log(x);
    }
    let choice;

    async function getSpaces() {
        const res = await fetch("http://localhost:8080/getSpaces");
        const resp = await res.json();

        return resp;
    }

    let promise = getSpaces();
    let promise2;

    let drycleaning = false, carwashing = false, repair = false;
    let dryworker = "", washworker = "", repairworker = "";
</script>

<main>
    {#if $login.first_name != ""}
        <Navbar color="dark" dark expand="md">
            <NavbarBrand href="/UserDashboard">Car Parking System</NavbarBrand>
            <Nav class="ms-auto" navbar>
                <NavItem>
                    <button class="orange" on:click={nav}>View Profile</button>
                </NavItem>
                <NavItem>
                    <button class="green" on:click={signOut}>Sign Out</button>
                </NavItem>
            </Nav>
        </Navbar>
        <br>
        <h3>Hello {$login.first_name} {$login.last_name}!</h3>
        <h1>Book your slot here</h1>
        <form on:submit|preventDefault={check}>
            <h6>Location</h6>
            {#await promise}
                <p>Loading spaces...</p>
            {:then spaces} 
            <select bind:value={choice} required>
                <option></option>
                {#each spaces as elem}
                    <option>{elem}</option>
                {/each}
            </select>
                
            {/await}
            
            <br><br>
            <h6>Date</h6>
            <input type="date" required bind:value={date_str}><br><br>
            <h6>Checkin</h6>
            <input type="time" required bind:value={time_1}><br><br>
            <h6>Checkout</h6>
            <input type="time" required bind:value={time_2}><br><br>
            <button class="blue" type="submit">Find Slots</button>
        </form>
        {#if bool}
            {#await promise2}
                    <p>Loading results...</p>
                {:then slots} 
                <Table bordered>
                    <tr>
                        <th>Slot</th>
                        <th>Price</th>
                        <th>Recommendation</th>
                        <th></th>
                    </tr>
                    {#each slots as slot}
                    <tr>
                        <td>{"Slot " + slot.slot}</td>
                        <td>{slot.isFree}</td>
                        <td>{slot.isRecommended}</td>
                        <td><div><Button on:click={openmodal(slots)} color="dark">Select Slot</Button></div></td>
                    </tr>
                    {/each}
                </Table>

            {/await}
        
        {/if}
        <Modal isOpen={bool1}>
            <ModalHeader>Preferences</ModalHeader>
            <ModalBody>
                <p>Services: 
                    <label><input type="checkbox" bind:checked={drycleaning}>Dry Cleaning</label>
                    <label><input type="checkbox" bind:checked={carwashing}>Car Washing</label>
                    <label><input type="checkbox" bind:checked={repair}>Repair & Maintenance</label>
                </p>
                {#if drycleaning}
                    <p>Dry Cleaning Worker: 
                        <select bind:value={dryworker}>
                            <option></option>
                            <option>Ramesh</option>
                            <option>Suresh</option>
                        </select>
                    </p>
                {/if}
                {#if carwashing}
                    <p>Car Washing Worker: 
                        <select bind:value={washworker}>
                            <option></option>
                            <option>Ramesh</option>
                            <option>Suresh</option>
                        </select>
                    </p>
                {/if}
                {#if repair}
                    <p>Repair & Maintenance Worker: 
                        <select bind:value={repairworker}>
                            <option></option>
                            <option>Ramesh</option>
                            <option>Suresh</option>
                        </select>
                    </p>
                {/if}
            </ModalBody>
            <ModalFooter>
                {#if ((drycleaning && dryworker != "")|| (carwashing && washworker != "") || (repair && repairworker != ""))}
                    <Button color="success">Make Payment</Button>
                {/if}
                <Button color="danger" on:click={openmodal}>Close</Button>
            </ModalFooter>
        </Modal>
    {:else}
        <p>You are not logged in! Kindly <a href="/UserLogin">login</a></p>
    {/if}
</main>

<style>
    main {
		text-align: center;
		padding: 1em;
        max-width: 1440px;
		margin: 0 auto;
	}

    h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 2em;
		font-weight: 100;
	}

    button {
        border-radius: 5px;
        margin: 5px;
    }

    .orange {
        background-color: rgb(255, 112, 60);
    }

    .green {
        background-color: chartreuse;
    }

    .blue {
        background-color: royalblue;
        color: white;
        border: 2px solid black;
    }

    .rec {
        color: green;
    }

    .notrec {
        color: red;
    }
</style>