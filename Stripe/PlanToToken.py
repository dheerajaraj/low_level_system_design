class PlanToToken:
    def __init__(self):
        self.input_token:int = 0
        self.output_token:int = 0
        self.records = 0

    def add_token(self, input_token: int, output_token: int) -> None:
        self.input_token += input_token
        self.output_token += output_token
        self.records += 1
