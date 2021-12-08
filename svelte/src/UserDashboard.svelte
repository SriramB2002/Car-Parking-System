<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
import { get_slot_changes } from "svelte/internal";
    import { Navbar, NavbarBrand, Nav, NavItem, Modal, ModalHeader, ModalBody, ModalFooter, Button, Table } from "sveltestrap/src";
    import { login } from "./stores";
    import { m } from "./stores";

    const today = new Date();

    let price = 0.0;

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
                car_model: "",
                balance: 0
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
        price = 0.0;
        let hour1 = "", hour2 = "", minute1 = "", minute2 = "";
        hour1 += time_1.charAt(0) + time_1.charAt(1);
        minute1 += time_1.charAt(3) + time_1.charAt(4);
        hour2 += time_2.charAt(0) + time_2.charAt(1);
        minute2 += time_2.charAt(3) + time_2.charAt(4);

        let h1=parseInt(hour1);
        let m1=parseInt(minute1);
        let h2=parseInt(hour2);
        let m2=parseInt(minute2);
        console.log(h1);
        console.log(m1);
        console.log(h2);
        console.log(m2);

        let temp_price = (h2*60 + m2) - (h1*60 + m1);
        temp_price *= 25.0/60;
        price += temp_price;
        console.log(price);
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
        console.log(resp);
        return resp; 
    }

    let bool1 = false;
    let s;
    const openmodal = (x) => {
        bool1 = !bool1;
        s = x;
    }
    let choice;

    async function getSpaces() {
        const res = await fetch("http://localhost:8080/getSpaces");
        const resp = await res.json();

        return resp;
    }

    function format(free) {
        let f = new Date(free);
        return f.toTimeString().split(' ')[0].substring(0, 5);
    }

    let promise = getSpaces();
    let promise2;

    let drycleaning = false, carwashing = false, repair = false;
    let dryworker = -1, washworker = -1, repairworker = -1;

    let serv1 = getDry();
    let serv2 = getWashing();
    let serv3 = getRepair();

    async function getDry() {
        try {
            const res = await fetch("http://localhost:8080/getworkers?service=Dry%20Cleaning");
            const resp = await res.json();
            console.log(resp);
            return resp;
        }
        catch(e) {
            console.log(e)
        }
    }

    async function getWashing() {
        try {
            const res = await fetch("http://localhost:8080/getworkers?service=Car%20Washing");
            const resp = await res.json();
            console.log(resp);
            return resp;
        }
        catch(e) {
            console.log(e)
        }
    }

    async function getRepair() {
        try {
            const res = await fetch("http://localhost:8080/getworkers?service=Repair%20Work");
            const resp = await res.json();
            console.log(resp);
            return resp;
        }
        catch(e) {
            console.log(e)
        }
    }

    async function book() {
        const x1 = (new Date(date_str + " " + time_1 + " GMT+0530")).getTime();
        const x2 = (new Date(date_str + " " + time_2 + " GMT+0530")).getTime();

        
        $login.balance -= 100;
        if($m == "") {
            if($login.balance < 100) {
                alert("Insufficient balance to make booking");
            }

            else {
                const res = await fetch("http://localhost:8080/book", {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    body: JSON.stringify({
                        bookingID: "",
                        checkIn: x1,
                        checkOut: x2,
                        cleaningWorker: dryworker,
                        washingWorker: washworker,
                        repairworker: repairworker,
                        slotID: s,
                        userID: $login.id
                    })
                })

                alert("BOOKING DONE");
            }
        }

            else {
                const res = await fetch("http://localhost:8080/updateBooking", {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    body: JSON.stringify({
                        bookingID: $m,
                        checkIn: x1,
                        checkOut: x2,
                        cleaningWorker: dryworker,
                        washingWorker: washworker,
                        repairworker: repairworker,
                        slotID: s,
                        userID: $login.id
                    })
                })

                alert("BOOKING MODIFIED");
            }

            drycleaning = false, carwashing = false, repair = false;
            dryworker = -1, washworker = -1, repairworker = -1;
            s = -1;
            bool1 = !bool1;

            
            updateProfile();
        
        

        
    }

    async function updateProfile(){
        const res = await fetch("http://localhost:8080/updateUser", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify($login)
         });
    }
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
        {#if $m == ""}
            <h1>Book your slot here</h1>
            {:else}
            <h1>Modify booking {$m}</h1>
        {/if}

        
        
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
                        <th></th>
                        <th></th>
                    </tr>
                    {#each slots as slot}
                    <tr>
                        <td>{"Slot " + slot.slot}</td>
                        <td>{price}</td>
                        {#if slot.isRecommended}
                            <td class="rec">Recommended</td>
                        {:else}
                            <td class="notrec">Not Recommended</td>
                        {/if}
                        {#if slot.isFree}
                            <td><div><Button on:click={openmodal(slot.slot)} color="dark">Select Slot</Button></div></td>
                        {:else}
                            <td><div><Button disabled color="dark">Available at {format(slot.freeTime)}</Button></div></td>
                        {/if}
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
                        {#await serv1}
                            <p>Please wait...</p>
                        {:then workers} 
                        <select bind:value={dryworker}>
                            <option></option>
                            {#each workers as worker}
                                <option value = {worker.id}>{worker.name}</option>
                            {/each}
                            
                        </select>
                        {/await}
                        
                    </p>
                {/if}
                {#if carwashing}
                    <p>Car Washing Worker: 
                        {#await serv2}
                            <p>Please wait...</p>
                        {:then workers} 
                        <select bind:value={washworker}>
                            <option></option>
                            {#each workers as worker}
                                <option value = {worker.id}>{worker.name}</option>
                            {/each}
                            
                        </select>
                        {/await}
                    </p>
                {/if}
                {#if repair}
                    <p>Repair & Maintenance Worker: 
                        {#await serv3}
                            <p>Please wait...</p>
                        {:then workers} 
                        <select bind:value={repairworker}>
                            <option></option>
                            {#each workers as worker}
                                <option value = {worker.id}>{worker.name}</option>
                            {/each}
                            
                        </select>
                        {/await}
                    </p>
                {/if}
            </ModalBody>
            <ModalFooter>
                {#if ((drycleaning || carwashing || repair) && (!drycleaning || dryworker != -1) && (!carwashing || washworker != -1) && (!repair || repairworker != -1))}
                    <Button color="success" on:click={book}>Make Payment</Button>
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