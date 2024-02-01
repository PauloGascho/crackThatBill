
<template>
    <div>
        <form @submit.prevent="fetchData" class="simple-form">
            <label for="userValues">Valores do usuário (separados por vírgula)</label>
            <input v-model="userValues" type="text" id="userValues" placeholder="Exemplo: 40,2"/>

            <label for="friendValues">Valores do amigo (separados por vírgula)</label>
            <input v-model="friendValues" type="text" id="friendValues" placeholder="Exemplo: 40,2"/>

            <label for="deliveryValue">Valores da entrega</label>
            <input v-model="deliveryValue" type="text" id="deliveryValue" placeholder="Exemplo: 8 ou 0"/>

            <label for="descountValue">Valores do desconto</label>
            <input v-model="descountValue" type="text" id="descountValue" placeholder="Exemplo: 20 ou 0"/>

            <label>
            <input v-model="isPercentualDescount" type="checkbox" />
                Desconto em porcentagem
            </label>

            <button type="submit">Calcular</button>
        </form>

        <div v-if="responseData">
            <h2>Calculo:</h2>
            <pre>Conta do usuário: R$ {{ responseData.userBill }}</pre>
            <pre>Conta do amigo: R$ {{ responseData.friendBill }}</pre>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";

export default class HomeForm extends Vue {
    userValues = '';
    friendValues = '';
    deliveryValue = '';
    descountValue = '';
    isPercentualDescount = false;
    responseData = null;
    

  async fetchData() {
    try {
      const url = 'http://localhost:3000/crackThatBill';
      const queryParams = new URLSearchParams({
        userValues: this.userValues,
        friendValues: this.friendValues,
        deliveryValue: this.deliveryValue,
        descountValue: this.descountValue,
        isPercentualDescount: this.isPercentualDescount.toString(),
      });

      const fullUrl = `${url}?${queryParams.toString()}`;
      const response = await fetch(fullUrl);

      this.responseData = await response.json();
    } catch (error) {
      console.error('Erro ao buscar dados:', error);
    }
  }
}
</script>

<style scoped>
.simple-form {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

label {
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
  box-sizing: border-box;
}

button {
  background-color: #bb213b;
  color: rgb(255, 255, 255);
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #b64d5e;
}
</style>