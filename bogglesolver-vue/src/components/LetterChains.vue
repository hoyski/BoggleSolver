<template>
  <div id="boggle-solver">
    <h1>Boggle Solver</h1>
    <div id="letters">
      <span>Letters:</span>
      <input
        @input="solveBoard"
        v-model="characters"
        id="charactersInput"
        size="25"
        ref="charactersInput"
      />
      <button class="btn btn-outline-dark" @click="clearLetters">Clear</button>
      <div id="helper-text">
        Enter the 16 letters on the board row by row.<br />
        Enter only Q for the Qu die.
      </div>
    </div>

    <div id="board-wrapper">
      <div id="board">
        <div class="tile-row" v-for="y in 4" v-bind:key="y">
          <div class="tile" v-for="x in 4" v-bind:key="(y - 1) * 4 + (x - 1)">
            {{ charAt(x, y) }}
          </div>
        </div>
      </div>
    </div>
    <hr />
    <span
      v-for="(letterChain, index) in letterChains"
      :key="index"
      @mouseenter="showChain(index)"
    >
      <!-- @mouseleave="clearLines()" -->
      <hr
        v-if="
          index > 0 &&
          letterChains[index].word.length > 3 &&
          letterChains[index - 1].word.length != letterChains[index].word.length
        "
      />
      <span class="word">{{ letterChain.word }}</span>
    </span>
    <div>
      <svg id="chainSvg" viewBox="0 0 4 4"></svg>
    </div>
  </div>
</template>

<script>
import boggleSolverService from "@/services/BoggleSolverService";

const colorList = [
  "green",
  "red",
  "blue",
  "yellow",
  "cyan",
  "orange",
  "purple",
];

export default {
  name: "BoggleWords",
  data() {
    return {
      characters: "",
      letterChains: [],
    };
  },
  methods: {
    solveBoard() {
      this.clearLines();
      this.characters = this.scrubChars(this.characters);
      if (this.characters.length > 16) {
        this.characters = this.characters.substring(0, 16);
      }

      if (this.characters.length == 16) {
        boggleSolverService.solveBoard(this.characters).then((response) => {
          this.letterChains = response.data;
        });
      } else {
        this.letterChains = [];
      }
    },
    charAt(x, y) {
      let pos = (y - 1) * 4 + (x - 1);
      let char = this.characters.charAt(pos);
      return char === "Q" ? "Qu" : char;
    },
    clearLetters() {
      this.characters = "";
      this.letterChains = [];
      this.clearLines();
      document.getElementById("charactersInput").focus();
    },
    scrubChars(letters, allowUnderscore = false) {
      var scrubbedChars = "";

      for (var i = 0; i < letters.length; ++i) {
        var curChar = letters.charAt(i).toUpperCase();
        if (
          (curChar >= "A" && curChar <= "Z") ||
          (curChar === "_" && allowUnderscore)
        ) {
          scrubbedChars += curChar;
        }
      }
      return scrubbedChars;
    },
    showChain(idx) {
      this.clearLines();

      // Add lines for the selected chain
      let chainSvg = document.getElementById("chainSvg");

      for (
        let chn = 0;
        chn < this.letterChains[idx].letterChains.length;
        chn++
      ) {
        let chain = this.letterChains[idx].letterChains[chn].letters;
        for (let i = 1; i < chain.length; i++) {
          const lineElem = document.createElementNS(
            "http://www.w3.org/2000/svg",
            "line"
          );
          lineElem.setAttribute(
            "style",
            `stroke: ${colorList[chn % colorList.length]}`
          );
          lineElem.setAttribute("x1", chain[i].x + 0.5);
          lineElem.setAttribute("y1", chain[i].y + 0.5);
          lineElem.setAttribute("x2", chain[i - 1].x + 0.5);
          lineElem.setAttribute("y2", chain[i - 1].y + 0.5);

          chainSvg.append(lineElem);
        }
      }
    },
    placeSvgOverBoard() {
      // Move svg to be on top of the board
      let boardRect = document.getElementById("board").getBoundingClientRect();
      let svgElem = document.getElementById("chainSvg");

      svgElem.style.top = boardRect.top + "px";
      svgElem.style.left = boardRect.left + "px";
    },
    clearLines() {
      let chainSvg = document.getElementById("chainSvg");

      // Remove the previous lines, if any
      let lines = chainSvg.querySelectorAll("line");
      for (let line of lines) {
        chainSvg.removeChild(line);
      }
    },
  },
  mounted() {
    this.$refs["charactersInput"].focus();

    window.addEventListener("resize", this.placeSvgOverBoard);

    this.placeSvgOverBoard();
  },
};
</script>

<style>
* {
  box-sizing: border-box;
}

#boggle-solver {
  width: 80%;
  margin: 0 auto;
}

#letters > * {
  margin-right: 10px;
}

#letters {
  margin-bottom: 10px;
}

#helper-text {
  font-size: 0.8rem;
  font-style: italic;
  margin-bottom: 1rem;
}

#board-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
}

#chainSvg {
  position: absolute;
  width: 300px;
  height: 300px;
}

#board {
  width: 300px;
  height: 300px;
  display: grid;
  grid-template-rows: repeat(4, 1fr);
  gap: 5px;
}

.tile-row {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 5px;
}

.tile {
  border-radius: 12px;
  border: 2px solid darkgray;
  width: 100%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  font-size: 3rem;
  line-height: 3rem;
  font-weight: bold;
  vertical-align: middle;
  color: #08b1c4;
}

.word {
  display: inline-block;
  font-family: monospace;
  margin-right: 1em;
}

.word:hover {
  text-decoration: underline;
  cursor: pointer;
}

line {
  stroke: green;
  stroke-width: 0.25;
  stroke-linecap: round;
  stroke-opacity: 0.25;
}
</style>