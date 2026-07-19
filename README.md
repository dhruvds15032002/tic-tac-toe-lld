# Tic Tac Toe — LLD (Low Level Design)

A machine-coding style implementation of Tic Tac Toe following a structured
interview approach, built for NxN boards with extensible win-checking logic.

## Design Patterns Used

- **Strategy Pattern** — `WinningStrategy` interface with `RowWinStrategy`,
  `ColumnWinStrategy`, `DiagonalWinStrategy` implementations. New win
  conditions can be added without touching `Game`.

## Approach Followed

### 1. Requirements Clarification
- How many players — 2 or N?
- Board size — fixed 3x3 or NxN?
- Symbols — fixed (X/O) or extensible?
- Win conditions — row/column/diagonal only, or custom patterns too?

### 2. Entity Identification
Extracted nouns from the problem statement:
`Symbol`, `GameStatus`, `Player`, `Board`, `WinningStrategy`, `Game`

### 3. Relationship Mapping
- `Player` HAS-A `Symbol`
- `Board` HAS-A grid of `Symbol`
- `RowWinStrategy` / `ColumnWinStrategy` / `DiagonalWinStrategy` IS-A `WinningStrategy`
- `Game` HAS-A `Board`, players (`Deque<Player>` for turn rotation),
  `List<WinningStrategy>`, `GameStatus`

### 4. Build Order (skeleton-first, then fill logic)
1. Enums (`GameStatus`) — zero dependencies
2. `Symbol` — built as an extensible wrapper class (not enum) with
   `equals()` / `hashCode()` / `toString()` overridden for value-based comparison
3. `Player` — immutable POJO
4. `Board` — validation via private helper (`validateCell`), optimized
   `isFull()` using a `filledCells` counter instead of O(N²) scan
5. `WinningStrategy` interface, then concrete strategies
6. `Game` — orchestrator tying everything together
7. `Main` — client demo

## Key Design Decisions

- **Symbol as a class, not enum** — allows runtime-defined symbols for
  N-player extensibility; required overriding `equals()`/`hashCode()` since
  default object comparison is reference-based, not value-based.
- **Deque for turn management** — `removeFirst()` current player, process
  move, `addLast()` back if game continues. Scales to N players.
- **Win-check before draw-check ordering** — the last move on a full board
  could simultaneously complete a win; checking win first avoids
  misreporting a win as a draw.
- **DiagonalWinStrategy checks both diagonals independently** (not
  if-else) — the center cell of an odd-sized board lies on both diagonals.
- **IllegalArgumentException vs IllegalStateException** — invalid
  row/column (bad input) uses `IllegalArgumentException`; placing on an
  already-occupied cell (valid input, invalid state) uses
  `IllegalStateException`.

## Folder Structure

src/
├── enums/
│   └── GameStatus.java
├── model/
│   ├── Symbol.java
│   ├── Player.java
│   └── Board.java
├── strategy/
│   ├── WinningStrategy.java
│   ├── RowWinStrategy.java
│   ├── ColumnWinStrategy.java
│   └── DiagonalWinStrategy.java
├── Game.java
└── App.java

## How to Run

```bash
javac -d bin src/App.java src/enums/*.java src/model/*.java src/strategy/*.java src/game/*.java
java -cp bin App
```