from User import User

class ChatBillingCalculator:
    pg_plan:str = "payg"
    fixed_plan:str = "fixed"
    fixed_fee:float = 15.00
    fixed_input_token:int = 40000
    fixed_output_token:int = 20000
    output_string_format = "{username}: ${cost}\n"

    def __init__(self):
        self.user_map = {}

    def calculate_monthly_billing(self, chat_sessions: [str]) -> [str]:
        res = []
        for item in chat_sessions:
            session_detail = item.split(',')
            username = session_detail[0]
            input_token = int(session_detail[1])
            output_token = int(session_detail[2])
            plan = session_detail[3]
            print(str(input_token )+ ", " + str(output_token) + ", " + plan)
            if plan != self.pg_plan and plan != self.fixed_plan:
                continue

            usr = User(username) if username not in self.user_map else self.user_map[username]
            if plan == self.pg_plan:
                usr.pg_plan.add_token(input_token, output_token)
            elif plan == self.fixed_plan:
                usr.fixed_plan.add_token(input_token, output_token)

            if username not in self.user_map:
                self.user_map[username] = usr

        sorted_data = dict(sorted(self.user_map.items()))
        for key, value in sorted_data.items():
            if value.pg_plan.records != 0:
                self.pay_g_calculation(value)
            if value.fixed_plan.records != 0:
                self.fixed_pricing(value)
            res.append(self.output_string_format.format(username=key, cost=value.cost))
        return res

    def pay_g_calculation(self, usr: User) -> None:
        input_cost = usr.pg_plan.input_token//100 * 0.03
        output_cost = usr.pg_plan.output_token//100 * 0.04
        usr.cost += (input_cost + output_cost)

    def fixed_pricing(self, usr: User) -> None:
        fp_fraction = usr.fixed_plan.records / (usr.pg_plan.records + usr.fixed_plan.records)
        input_limit = fp_fraction * self.fixed_input_token
        output_limit = fp_fraction * self.fixed_output_token
        overage_input = usr.fixed_plan.input_token - input_limit if usr.fixed_plan.input_token > input_limit else 0
        overage_output = usr.fixed_plan.output_token - output_limit if usr.fixed_plan.output_token > output_limit else 0
        user_cost = (15*fp_fraction + (overage_input//100 * 0.03 ) + (overage_output//100 * 0.04))
        #print(f"input_token: {usr.fixed_plan.input_token}, output_token: {usr.fixed_plan.output_token}, overage_input: {overage_input}, overage_output: {overage_output}, user_cost: {user_cost}")
        usr.cost += user_cost